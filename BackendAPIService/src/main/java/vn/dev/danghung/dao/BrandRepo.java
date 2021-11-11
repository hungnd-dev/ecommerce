package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.Brand;
@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {
    Brand findByName(String name);
}
