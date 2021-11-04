package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.Cart;
@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    Cart findByUserIdIsAndOrderStateIs(int userId, int orderState);
}
