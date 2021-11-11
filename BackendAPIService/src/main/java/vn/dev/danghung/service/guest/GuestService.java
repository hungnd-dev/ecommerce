package vn.dev.danghung.service.guest;

import vn.dev.danghung.entities.Brand;
import vn.dev.danghung.entities.Product;
import vn.dev.danghung.model.response.ProductResponse;

import java.util.List;
public interface GuestService {
    List<ProductResponse> getAllProduct() throws Exception;
    List<Product> findProduct(String searchKey) throws Exception;
    List<Brand> getAllBrand() throws Exception;
}
