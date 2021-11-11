package vn.dev.danghung.controller.access;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.controller.BaseController;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.global.StatusCode;
import vn.dev.danghung.model.request.AccessRequest;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.model.response.AccessResponse;
import vn.dev.danghung.service.access.AccessService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class AccessController extends BaseController {
    @Autowired
    private AccessService accessService;
    @PostMapping(value = "/sign_in", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response UserLogIn(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AccessRequest accessRequest
//            @RequestParam("username") String username,
//            @RequestParam("password") String password
    ){
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = new Response();
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        try{
//            AccessRequest accessRequest = new AccessRequest();
//            accessRequest.setUsername(username);
//            accessRequest.setPassword(password);
            AccessResponse accessResponse = accessService.getJwtToken(accessRequest);
            if(!accessResponse.getUserResponse().getRole().equalsIgnoreCase("client")){
                throw new CommonException("Username or password invalid", ErrorCode.USER_OR_PASSWORD_INVALID);
            }
            serviceReponse = accessResponse;
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

    @PostMapping(value = "/sign_in/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response AdminLogIn(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AccessRequest accessRequest
//            @RequestParam("username") String username,
//            @RequestParam("password") String password
    ){
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = new Response();
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        try{
//            AccessRequest accessRequest = new AccessRequest();
//            accessRequest.setUsername(username);
//            accessRequest.setPassword(password);
            AccessResponse accessResponse = accessService.getJwtToken(accessRequest);
            if(!accessResponse.getUserResponse().getRole().equalsIgnoreCase("admin")){
                throw new CommonException("Username or password invalid", ErrorCode.USER_OR_PASSWORD_INVALID);
            }
            serviceReponse = accessResponse;
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

    @PostMapping(value = "/sign_up", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response signUp(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody UserRequest userRequest
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            @RequestParam("fullname") String fullname,
//            @RequestParam("telephone") String telephone
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
//            UserRequest userRequest = new UserRequest();
//            userRequest.setUsername(username);
//            userRequest.setPassword(password);
//            userRequest.setFullname(fullname);
//            userRequest.setTelephone(telephone);
            serviceReponse = accessService.create(userRequest);
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
