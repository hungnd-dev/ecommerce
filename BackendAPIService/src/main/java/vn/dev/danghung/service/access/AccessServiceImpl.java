package vn.dev.danghung.service.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.dev.danghung.adapter.UserAdapter;
import vn.dev.danghung.dao.UserRepo;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.model.request.AccessRequest;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.model.response.AccessResponse;
import vn.dev.danghung.model.response.UserResponse;
import vn.dev.danghung.policy.UserRule;
import vn.dev.danghung.security.config.JwtTokenUtil;
import vn.dev.danghung.service.AbstractService;

import java.util.ArrayList;

@Service
public class AccessServiceImpl extends AbstractService implements AccessService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userAdapter")
    private UserAdapter userAdapter;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRule userRule;

    @Override
    public AccessResponse getJwtToken(AccessRequest accessRequest) throws Exception {
        //check username password
        authenticate(accessRequest.getUsername(), accessRequest.getPassword());
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(accessRequest.getUsername());
        //generate token
        String token = jwtTokenUtil.generateToken(userDetails);
        Long exp = 3600000L;
        //get user from token
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserResponse userResponse = userAdapter.transform(userRepo.findUserByUsername(username));
        AccessResponse accessResponse = new AccessResponse(token, exp, userResponse);

        return accessResponse;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new CommonException(e.getMessage(), ErrorCode.USER_OR_PASSWORD_INVALID);
        } catch (BadCredentialsException e) {
            throw new CommonException(e.getMessage(), ErrorCode.USER_OR_PASSWORD_INVALID);
        }
    }

    @Override
    public Object create(UserRequest userRequest) throws Exception {
        userRule.verify(userRequest);
        User user = userAdapter.transform(userRequest);
        user.setCreatedAt(System.currentTimeMillis());
        user.setRole("client");
        user.setState(1);
        userRepo.save(user);
        return null;
    }
}
