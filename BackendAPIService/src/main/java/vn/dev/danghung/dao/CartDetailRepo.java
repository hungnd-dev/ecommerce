package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.CartDetail;
import java.util.List;
@Repository
public interface CartDetailRepo extends JpaRepository<CartDetail,Integer> {
    CartDetail findByCartIdIsAndProductIdIs(int cardId, int productId);

    List<CartDetail> findAllByCartId(int cartId);
}
