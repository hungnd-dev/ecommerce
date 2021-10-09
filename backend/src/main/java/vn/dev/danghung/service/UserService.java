package vn.dev.danghung.service;

import org.springframework.stereotype.Service;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.model.request.UserRequest;


public interface UserService {
    Response getUserByUserName(String username) throws Exception;

    Response create(UserRequest userRequest) throws Exception;
}
