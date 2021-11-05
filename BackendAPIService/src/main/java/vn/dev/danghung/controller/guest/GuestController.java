package vn.dev.danghung.controller.guest;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.controller.BaseController;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.StatusCode;
import vn.dev.danghung.service.guest.GuestService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * guest controller: api for all role
 * view all product
 * search product
 * author: Nguyễn Đăng Hùng HUST K63
*/

@RestController
public class GuestController extends BaseController {
    @Autowired
    private GuestService guestService;

    @GetMapping(value = "/product/all")
    public Response getAllProduct(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = new Object();
        Response svResponse = null;
        try{
            serviceReponse = guestService.getAllProduct();
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

    @GetMapping(value = "/product/search")
    public Response getProductBySearchKey(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("key") String sKey
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            serviceReponse = guestService.findProduct(sKey);
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
    @GetMapping(value = "/brand/all")
    public Response getAllBrand(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = new Object();
        Response svResponse = null;
        try{
            serviceReponse = guestService.getAllBrand();
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
