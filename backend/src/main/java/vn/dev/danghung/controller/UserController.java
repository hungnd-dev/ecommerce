package vn.dev.danghung.controller;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.service.SignActionService;
import vn.dev.danghung.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @Autowired
    private SignActionService signActionService;

    @GetMapping(value = "/user/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUserByToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String svcResponse;
        Response serverResponse;
        String mes = "";
        try {
            String username = signActionService.getUserNameByToken(request);
            serverResponse = userService.getUserByUserName(username);
            svcResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());
            return new ResponseEntity<>(svcResponse, HttpStatus.OK);

        } catch (UserException ue) {
            ue.printStackTrace();
            eLogger.error(ue.getMessage());
            svcResponse = buildFailureResponse(ue.getCode(), ue.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            eLogger.error("page me error: {}", e.getMessage());
            svcResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svcResponse, HttpStatus.OK);
    }
}
