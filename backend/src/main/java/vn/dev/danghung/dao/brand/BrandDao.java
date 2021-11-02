package vn.dev.danghung.dao.jdbc.brand;

import vn.dev.danghung.entities.Brand;
import java.util.List;
public interface BrandDao {
    Brand find(String id);
    List<Brand> findAll();
    void create(Brand brand);
    void update(Brand brand);
}
