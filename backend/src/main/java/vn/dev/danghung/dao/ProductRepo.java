package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.Product;
import java.util.List;
@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findAllByDesContains(String searchKey);

    List<Product> findAllByIdIn(List<Integer> productIdList);
}
