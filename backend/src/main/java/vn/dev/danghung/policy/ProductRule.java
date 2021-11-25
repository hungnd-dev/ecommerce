package vn.dev.danghung.policy;

import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.model.request.ProductRequest;

public interface ProductRule {
    void verify(ProductRequest productRequest) throws CommonException;
}
