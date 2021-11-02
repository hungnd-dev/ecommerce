package vn.dev.danghung.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.dev.danghung.dao.UserRepo;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.service.AbstractService;

import java.util.List;
@Component("userService")
public class UserService extends AbstractService implements EntityService<User> {
    @Autowired
    private UserRepo userRepo;
    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User findByName(String name) {
        return userRepo.findUserByUsername(name);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}
