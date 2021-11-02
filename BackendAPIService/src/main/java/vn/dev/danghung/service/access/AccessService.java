package vn.dev.danghung.service.access;

import vn.dev.danghung.model.request.AccessRequest;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.model.response.AccessResponse;

public interface AccessService {
    AccessResponse getJwtToken(AccessRequest accessRequest) throws Exception;

    Object create(UserRequest userRequest) throws Exception;
}
