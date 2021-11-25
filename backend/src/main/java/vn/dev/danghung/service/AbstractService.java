package vn.dev.danghung.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import vn.dev.danghung.dao.UserRepo;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.security.config.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

public class AbstractService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepo userRepo;

    protected static final Logger requestLogger = LogManager.getLogger("RequestLog");
    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");
    protected static final Logger cLogger = LogManager.getLogger("CacheLog");
    protected static final Logger pLogger = LogManager.getLogger("WorkerLog");


}
