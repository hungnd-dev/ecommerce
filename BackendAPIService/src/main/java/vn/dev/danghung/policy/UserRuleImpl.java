package vn.dev.danghung.policy;
import org.springframework.stereotype.Component;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.utils.CommonUtils;

@Component
public class UserRuleImpl extends AbstractRule implements UserRule{
    @Override
    public void verify(UserRequest userRequest) throws CommonException {
        if(CommonUtils.checkEmpty(userRequest.getUsername())){
            throw new CommonException("User is empty", ErrorCode.USERNAME_INVALID);
        }
        if(CommonUtils.checkEmpty(userRequest.getPassword())){
            throw new CommonException("Password is empty", ErrorCode.PASSWORD_INVALID);
        }
        if(CommonUtils.checkEmpty(userRequest.getTelephone())){
            throw new CommonException("Phone number is empty", ErrorCode.USER_PHONE_INVALID);
        }
        if(CommonUtils.checkEmpty(userRequest.getFullname())){
            throw new CommonException("Full name is empty", ErrorCode.USER_FULLNAME_INVALID);
        }
        super.verifyUserNamePassWord(userRequest);
        super.verifyPhoneNumber(userRequest);
        super.verifyFullName(userRequest);
    }

    @Override
    public void verifyPassword(UserRequest userRequest) throws CommonException {
        super.verifyUserNamePassWord(userRequest);
    }

    @Override
    public void verifyPhoneNumber(UserRequest userRequest) throws CommonException {
        super.verifyPhoneNumber(userRequest);
    }

    @Override
    public void verifyFullName(UserRequest userRequest) throws CommonException {
        super.verifyFullName(userRequest);
    }
}
