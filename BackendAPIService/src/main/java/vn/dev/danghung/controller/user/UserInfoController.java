package vn.dev.danghung.controller.user;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.dev.danghung.adapter.UserAdapter;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.controller.BaseController;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.exception.RoleException;
import vn.dev.danghung.global.StatusCode;
import vn.dev.danghung.model.request.OrderRequest;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.model.response.UserResponse;
import vn.dev.danghung.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class UserInfoController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userAdapter")
    private UserAdapter userAdapter;

    /**
     * @name user get account's info
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/user/info/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getMyInfo(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            User user = super.getUserByToken(request);
            super.checkUserState(user);
            serviceReponse = userAdapter.transform(user);
            svResponse = buildResponse(HttpStatus.OK.value(), StatusCode.SUCCESS,message,serviceReponse);
        } catch (CommonException c){
            message = c.getMessage();
            int code = c.getCode();
            svResponse = buildResponse(code, StatusCode.FAILURE,message,serviceReponse);

        } catch(Exception e) {
            message = "an error occurred";
            svResponse = buildResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), StatusCode.FAILURE,message,serviceReponse);
        }
        requestLogger.info("finish request {} in {}", requestUri,sw.stop());
        return svResponse;
    }

    /**
     * @name user change info account
     * @param request
     * @param response
     * @param telephone
     * @param fullname
     * @param password
     * @return
     */
    @GetMapping("/user/info/change")
    public Response changeUserInfo(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "telephone", defaultValue = "") String telephone,
            @RequestParam(value = "fullname", defaultValue = "") String fullname,
            @RequestParam(value = "password", defaultValue = "") String password
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            UserRequest userRequest = new UserRequest();
            userRequest.setFullname(fullname);
            userRequest.setPassword(password);
            userRequest.setTelephone(telephone);
            User user = super.getUserByToken(request);
            super.checkUserState(user);
            serviceReponse = userService.changeInfoUser(user,userRequest);
            svResponse = buildResponse(HttpStatus.OK.value(), StatusCode.SUCCESS,message,serviceReponse);
        } catch (CommonException c){
            message = c.getMessage();
            int code = c.getCode();
            svResponse = buildResponse(code, StatusCode.FAILURE,message,serviceReponse);

        } catch(Exception e) {
            message = "an error occurred";
            svResponse = buildResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), StatusCode.FAILURE,message,serviceReponse);
        }
        requestLogger.info("finish request {} in {}", requestUri,sw.stop());
        return svResponse;
    }

}
