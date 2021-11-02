package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
