package vn.dev.danghung.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import vn.dev.danghung.builder.Response;

@CrossOrigin
public class BaseController {
    protected static final Logger requestLogger = LogManager.getLogger("RequestLog");
    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");
    protected static final Logger cLogger = LogManager.getLogger("CacheLog");
    protected static final Logger pLogger = LogManager.getLogger("WorkerLog");

    protected static Response buildResponse(int code, int status,String message, Object data){
        Response response = new Response();
        response.setCode(code);
        response.setData(data);
        response.setMessage(message);
        response.setStatus(status);
        return response;
    }
}
