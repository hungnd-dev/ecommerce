package vn.dev.danghung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dev.danghung.entities.User;
import java.util.List;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);

    List<User> findAllByRole(String role);
}
