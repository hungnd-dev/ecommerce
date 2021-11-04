package vn.dev.danghung.controller.user;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.controller.BaseController;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.exception.RoleException;
import vn.dev.danghung.global.StatusCode;
import vn.dev.danghung.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserCartController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * @name user add product to cart
     * @param request
     * @param response
     * @param productId
     * @return
     */
    //add product to my cart:
    @GetMapping("/user/cart/add/")
    public Response addToCart(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("product_id") int productId
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            User user = super.getUserByToken(request);
            super.checkUserState(user);
            serviceReponse = userService.addToCart(user,productId);
            svResponse = buildResponse(HttpStatus.OK.value(), StatusCode.SUCCESS,message,serviceReponse);
        } catch (RoleException r) {
            message = r.getMessage();
            int code = r.getCode();
            svResponse = buildResponse(code, StatusCode.FAILURE, message, serviceReponse);
        }
        catch (CommonException c){
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
     * @name user reduce product in cart
     * @param request
     * @param response
     * @param productId
     * @return
     */
    //reduce one product quantity in cart detail
    @GetMapping( "/user/cart/reduce/")
    public Response reduceProductInCart(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("product_id") int productId
//            @PathVariable("product_id") int productId
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            User user = super.getUserByToken(request);
            super.checkUserState(user);
            serviceReponse = userService.reduceInCart(user,productId);
            svResponse = buildResponse(HttpStatus.OK.value(), StatusCode.SUCCESS,message,serviceReponse);
        } catch (RoleException r) {
            message = r.getMessage();
            int code = r.getCode();
            svResponse = buildResponse(code, StatusCode.FAILURE, message, serviceReponse);
        }
        catch (CommonException c){
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
     * @name user cart over view
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/user/cart/view/")
    public Response cartOverView(
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
            serviceReponse = userService.overViewCart(user);
            svResponse = buildResponse(HttpStatus.OK.value(), StatusCode.SUCCESS,message,serviceReponse);
        } catch (RoleException r) {
            message = r.getMessage();
            int code = r.getCode();
            svResponse = buildResponse(code, StatusCode.FAILURE, message, serviceReponse);
        }
        catch (CommonException c){
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
