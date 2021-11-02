package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.CartDetail;
@Repository
public interface CartDetailRepo extends JpaRepository<CartDetail,Integer> {
}
