package vn.dev.danghung.adapter;

import org.springframework.stereotype.Component;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.model.response.UserResponse;
import vn.dev.danghung.utils.DateTimeUtils;

@Component("userAdapter")
public class UserAdapter implements EntitiesAdapter<User, UserResponse>{
    @Override
    public UserResponse transform(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setRole(user.getRole());
        userResponse.setTelephone(user.getTelephone());
        userResponse.setFullname(user.getFullname());
        userResponse.setCreatedAt(DateTimeUtils.generateTime(user.getCreatedAt()));
        userResponse.setUsername(user.getUsername());
        userResponse.setState(user.getState());
        return userResponse;
    }

    public User transform(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setFullname(userRequest.getFullname());
        user.setTelephone(userRequest.getTelephone());
        user.setCreatedAt(System.currentTimeMillis());
        user.setRole("client");
        user.setState(1);
        return user;
    }

}
