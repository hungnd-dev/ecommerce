package vn.dev.danghung.dao;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.Order;
import java.util.List;
@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findAllByUserId(int userId);

    List<Order> findAllByStateIs(int state);

    List<Order> findAllByCreateAtBetween(long fromDate, long toDate);
}
