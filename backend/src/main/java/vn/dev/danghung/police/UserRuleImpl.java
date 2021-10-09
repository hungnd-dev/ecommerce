package vn.dev.danghung.police;
import org.springframework.stereotype.Component;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.model.request.UserRequest;

@Component
public class UserRuleImpl extends AbstractRule implements UserRule{
    @Override
    public void verify(UserRequest userRequest) throws UserException {
        super.verifyUserNamePassWord(userRequest);
        super.verifyPhoneNumber(userRequest);
    }
}
