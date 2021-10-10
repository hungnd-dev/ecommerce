package vn.dev.danghung.adapter;

import org.springframework.stereotype.Component;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.model.response.UserResponse;
import vn.dev.danghung.utils.DateTimeUtils;

@Component("userAdapter")
public class UserAdapter implements EntityAdapter<User, UserResponse> {
    @Override
    public UserResponse transform(User entity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(entity.getUsername());
        userResponse.setFullname(entity.getFullName());
        userResponse.setTelephone(entity.getTelephone());
        userResponse.setCreatedAt(DateTimeUtils.generateTime(entity.getCreatedAt()));
        userResponse.setState(entity.getState());
        userResponse.setRole(entity.getRole());
        return userResponse;
    }
}
