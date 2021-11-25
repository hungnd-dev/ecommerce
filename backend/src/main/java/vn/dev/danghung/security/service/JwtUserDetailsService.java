package vn.dev.danghung.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.dev.danghung.dao.UserRepo;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.service.AbstractService;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService extends AbstractService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        Set<GrantedAuthority> role = new HashSet<>();
        try {
            user = userRepo.findUserByUsername(username);
        } catch (Exception e) {
            eLogger.error("error in jwt user detail service, reason: {}", e.getMessage());
        }
        if(user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        role.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), role);
    }
}
