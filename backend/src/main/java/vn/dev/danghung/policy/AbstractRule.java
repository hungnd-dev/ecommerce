package vn.dev.danghung.policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.model.request.UserRequest;

import java.text.SimpleDateFormat;

public class AbstractRule {
    @Autowired
    private PasswordEncoder passwordEncoder;
    protected SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    void verifyUserNamePassWord(UserRequest userRequest) throws CommonException {
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();
        String regex = "^[A-Za-z]\\w{5,29}$";
        //check username
        if(!username.matches(regex)){
            throw new CommonException("username invalid", ErrorCode.USERNAME_INVALID);
        }
        //check password
        if(!password.matches(regex)){
            throw new CommonException("password invalid", ErrorCode.PASSWORD_INVALID);
        }
        userRequest.setPassword(passwordEncoder.encode(password));
    };

    void verifyPhoneNumber(UserRequest userRequest) throws CommonException{
        String phoneNumber = userRequest.getTelephone();
        String regex = "^[0-9]\\d{9}$";
        if(!phoneNumber.matches(regex)){
            throw new CommonException("phone number invalid",ErrorCode.USER_PHONE_INVALID);
        }
    }

    void verifyFullName(UserRequest userRequest) throws CommonException{
        String fName = userRequest.getFullname();
        if(fName.length() < 8){
            throw new CommonException("fullname is very short", ErrorCode.USER_FULLNAME_INVALID);
        }
    }
}
