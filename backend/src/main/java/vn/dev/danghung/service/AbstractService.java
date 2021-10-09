package vn.dev.danghung.service;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.global.UserErrorCode;
import vn.dev.danghung.security.config.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

public class AbstractService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");
    protected static final Logger cLogger = LogManager.getLogger("CacheLog");
    protected static final Logger pLogger = LogManager.getLogger("WorkerLog");

    protected String getUserNameByToken(HttpServletRequest request) throws UserException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new UserException("Unable to get JWT Token", UserErrorCode.JWT_ERROR);
            } catch (ExpiredJwtException e) {
                throw new UserException("JWT Token has expired", UserErrorCode.JWT_ERROR);
            }
        } else {
            eLogger.warn("JWT Token does not begin with Bearer String");
        }
        return username;
    }
}
