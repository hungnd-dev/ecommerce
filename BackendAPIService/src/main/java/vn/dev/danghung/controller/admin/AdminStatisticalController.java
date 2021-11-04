package vn.dev.danghung.controller.admin;

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
import vn.dev.danghung.service.admin.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AdminStatisticalController extends BaseController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/statistical")
    public Response statisticalOrder(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("from_date") String fromDate,
            @RequestParam("to_date") String toDate
    ){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String message = "Request complete";
        Object serviceReponse = null;
        Response svResponse = null;
        try{
            serviceReponse = adminService.viewTotalMoneyAndOrder(fromDate,toDate);
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
