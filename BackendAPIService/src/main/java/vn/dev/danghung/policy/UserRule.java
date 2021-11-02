package vn.dev.danghung.policy;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.model.request.UserRequest;

public interface UserRule {
    void verify(UserRequest userRequest) throws CommonException;
}
