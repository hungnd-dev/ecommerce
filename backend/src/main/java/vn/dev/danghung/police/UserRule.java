package vn.dev.danghung.police;


import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.model.request.UserRequest;

public interface UserRule {
    void verify(UserRequest userRequest) throws UserException;
}
