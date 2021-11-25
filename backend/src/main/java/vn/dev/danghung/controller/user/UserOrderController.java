package vn.dev.danghung.controller.user;

import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.controller.BaseController;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.exception.RoleException;
import vn.dev.danghung.global.StatusCode;
import vn.dev.danghung.model.request.OrderRequest;
import vn.dev.danghung.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class UserOrderController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * @name user create order
     * @param request
     * @param response
     * @param address
     * @param deliveryType
     * @param phoneReceive
     * @return
     */
    @GetMapping("/user/order/create")
    public Response createOrder(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("delivery") String deliveryType,
            @RequestParam("phone") String phoneReceive
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setName(name);
            orderRequest.setAddress(address);
            orderRequest.setDeliveryType(deliveryType);
            orderRequest.setPhoneReceive(phoneReceive);
            User user = super.getUserByToken(request);
            super.checkUserState(user);
            serviceReponse = userService.createOrder(user, orderRequest);
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
     * @name user order over view
     * @param request
     * @param response
     * @return
     */

    @GetMapping("/user/order/view")
    public Response viewOrderAll(
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
            serviceReponse = userService.viewAllOrder(user);
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

    @GetMapping("/user/order/confirm")
    public Response confirmOrder(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("order_id") Integer orderId
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            User user = super.getUserByToken(request);
            super.checkUserState(user);
            serviceReponse = userService.confirmOrder(user, orderId);
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

    @GetMapping("/user/order/reject")
    public Response rejectOrder(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("order_id") Integer orderId
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            User user = super.getUserByToken(request);
            super.checkUserState(user);
            serviceReponse = userService.rejectOrder(user, orderId);
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
