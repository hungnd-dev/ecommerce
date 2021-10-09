package vn.dev.danghung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.dev.danghung.adapter.UserAdapter;
import vn.dev.danghung.builder.Response;
import vn.dev.danghung.dao.user.UserDao;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.UserException;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.model.response.UserResponse;
import vn.dev.danghung.police.UserRule;

import java.sql.SQLException;

@Service
public class UserServiceImpl extends AbstractService implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("userAdapter")
    private UserAdapter userAdapter;

    @Autowired
    private UserRule userRule;

    @Override
    public Response getUserByUserName(String username) throws Exception {
        User user = userDao.find(username);
        if(user == null){
            throw new UserException("user not found", HttpStatus.NOT_FOUND.value());
        }
        UserResponse userResponse = userAdapter.transform(user);
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(userResponse)
                .build();
    }

    @Override
    public Response create(UserRequest userRequest) throws Exception {
        userRule.verify(userRequest);
        userDao.create(userRequest);
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("create employee successfully")
                .build();
    }
}
