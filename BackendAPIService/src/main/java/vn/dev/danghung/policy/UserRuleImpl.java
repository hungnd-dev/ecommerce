package vn.dev.danghung.policy;
import org.springframework.stereotype.Component;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.model.request.UserRequest;

@Component
public class UserRuleImpl extends AbstractRule implements UserRule{
    @Override
    public void verify(UserRequest userRequest) throws CommonException {
        super.verifyUserNamePassWord(userRequest);
        super.verifyPhoneNumber(userRequest);
    }
}
