package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.OrderDetail;
@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail,Integer> {
}
