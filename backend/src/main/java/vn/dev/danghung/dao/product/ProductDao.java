package vn.dev.danghung.dao.jdbc.product;

import vn.dev.danghung.entities.Product;
import vn.dev.danghung.model.request.ProductRequest;

import java.util.List;
public interface ProductDao {
    Product find(String id);
    List<Product> findAll();
    void create(ProductRequest productRequest);
    void update(ProductRequest productRequest);
}
