package vn.dev.danghung.police;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.global.UserErrorCode;
import vn.dev.danghung.model.request.UserRequest;

public class AbstractRule {
    @Autowired
    private PasswordEncoder passwordEncoder;


    void verifyUserNamePassWord(UserRequest userRequest) throws UserException {
        String username = userRequest.getUserName();
        String password = userRequest.getPassWord();
        String regex = "^[A-Za-z]\\w{5,29}$";
        //check username
        if(!username.matches(regex)){
            throw new UserException("username invalid", UserErrorCode.USERNAME_INVALID);
        }
        //check password
        if(!password.matches(regex)){
            throw new UserException("password invalid", UserErrorCode.PASSWORD_INVALID);
        }
        userRequest.setPassWord(passwordEncoder.encode(password));
    };

    void verifyPhoneNumber(UserRequest userRequest) throws UserException{
        String phoneNumber = userRequest.getPhone();
        String regex = "^[0-9]\\d{9}$";
        if(!phoneNumber.matches(regex)){
            throw new UserException("phone number invalid",UserErrorCode.PHONE_INVALID);
        }
    }
}
