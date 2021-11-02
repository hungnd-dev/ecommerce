package vn.dev.danghung.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.dev.danghung.adapter.UserAdapter;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.dao.user.UserDao;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.global.UserErrorCode;
import vn.dev.danghung.model.request.SignRequest;
import vn.dev.danghung.model.response.SignResponse;
import vn.dev.danghung.model.response.UserResponse;
import vn.dev.danghung.security.config.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

@Service
public class SignActionServiceImpl extends AbstractService implements SignActionService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("userAdapter")
    private UserAdapter userAdapter;

    @Override
    public Response getJwtTokenRing(SignRequest signRequest) throws UserException,Exception {
        //check username password
        authenticate(signRequest.getUsername(), signRequest.getPassword());
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(signRequest.getUsername());
        //generate token
        String token = jwtTokenUtil.generateToken(userDetails);
        Long exp = 3600000L;
        //get user from token
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserResponse userResponse = userAdapter.transform(userDao.find(username));
        SignResponse signResponse = new SignResponse(token, exp, userResponse);

        Response response = new Response.Builder(1, HttpStatus.OK.value())
                .buildData(signResponse)
                .build();

        return response;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserException(e.getMessage(), UserErrorCode.USER_OR_PASSWORD_INVALID);
        } catch (BadCredentialsException e) {
            throw new UserException(e.getMessage(), UserErrorCode.USER_OR_PASSWORD_INVALID);
        }
    }

    @Override
    public String getUserNameByToken(HttpServletRequest request) throws UserException {
        return super.getUserNameByToken(request);
    }
}
