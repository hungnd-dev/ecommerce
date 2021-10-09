package vn.dev.danghung.service;

import org.springframework.stereotype.Service;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.model.request.SignRequest;

import javax.servlet.http.HttpServletRequest;

public interface SignActionService {
    Response getJwtTokenRing(SignRequest signRequest) throws Exception;

    String getUserNameByToken(HttpServletRequest request) throws UserException;
}
