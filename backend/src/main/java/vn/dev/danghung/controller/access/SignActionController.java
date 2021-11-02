package vn.dev.danghung.controller;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.model.request.SignRequest;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.service.SignActionService;
import vn.dev.danghung.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@CrossOrigin
public class SignActionController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private SignActionService signActionService;

    @PostMapping(value = "/sign_in", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createAuthenticationToken(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody SignRequest signRequest
    ) {
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String svcResponse;
        Object serverResponse;
        try{
            serverResponse = signActionService.getJwtTokenRing(signRequest);
            svcResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());

        }catch (UserException se) {
            eLogger.error("sign in user error: {}", se.getMessage());
            svcResponse = buildFailureResponse(se.getCode(), se.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            eLogger.error("sign in user error: {}", e.getMessage());
            svcResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svcResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/sign_up", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> postUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody UserRequest userRequest

    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String svcResponse;
        Object serverResponse;
        try {
            serverResponse = userService.create(userRequest);
            svcResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());

        } catch (UserException se) {
            eLogger.error("create user error: {}", se.getMessage());
            svcResponse = buildFailureResponse(se.getCode(), se.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            eLogger.error("create user error: {}", e.getMessage());
            svcResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svcResponse, HttpStatus.OK);
    }
}
