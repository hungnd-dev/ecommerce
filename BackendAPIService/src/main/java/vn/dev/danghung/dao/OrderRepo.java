package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
}
