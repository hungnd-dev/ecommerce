package vn.dev.danghung.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.dao.UserRepo;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.exception.RoleException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.security.config.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
public class BaseController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepo userRepo;

    protected static final Logger requestLogger = LogManager.getLogger("RequestLog");
    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");
    protected static final Logger cLogger = LogManager.getLogger("CacheLog");
    protected static final Logger pLogger = LogManager.getLogger("WorkerLog");

    protected static Response buildResponse(int code, int status, String message, Object data) {
        Response response = new Response();
        response.setCode(code);
        response.setData(data);
        response.setMessage(message);
        response.setStatus(status);
        return response;
    }

    public User getUserByToken(HttpServletRequest request) throws Exception {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try{
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            }catch (IllegalArgumentException e) {
                throw new CommonException("JWT Error", ErrorCode.JWT_ERROR);
            } catch (ExpiredJwtException e) {
                throw new CommonException("JWT Error", ErrorCode.JWT_ERROR);
            }
        }
        return userRepo.findUserByUsername(username);
    }

    protected String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        request.getParameterMap().keySet().stream().sorted().forEach(k -> {
            String v = request.getParameter(k);
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(k).append("=").append(v);
        });
        return sb.toString();
    }

    protected void checkUserState(User user) throws RoleException {
        if(user.getState() == 0){
            throw new RoleException("Your account is being blocked, please contact to admin to open", ErrorCode.USER_ACCOUNT_IS_BLOCKED);
        }
    }
}
