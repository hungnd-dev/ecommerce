package vn.dev.danghung.controller.admin;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.dev.danghung.adapter.UserAdapter;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.controller.BaseController;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.global.StatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AdminAuthenValidateController extends BaseController {
    @Autowired
    @Qualifier("userAdapter")
    private UserAdapter userAdapter;

    @GetMapping("/admin/authen/validate/token")
    public Response validateToken(
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
//            serviceReponse = adminService.addProduct(productRequest);
            serviceReponse = userAdapter.transform(user);;
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
