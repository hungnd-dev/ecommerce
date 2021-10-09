package vn.dev.danghung.dao.user;

import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.model.request.UserRequest;

import java.sql.SQLException;

public interface UserDao {
    User find(String username) throws SQLException;

    void create(UserRequest userRequest) throws SQLException;
}
