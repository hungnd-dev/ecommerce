package vn.dev.danghung.service.user;

import vn.dev.danghung.entities.User;
import vn.dev.danghung.model.request.OrderRequest;
import vn.dev.danghung.model.request.UserRequest;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    Object addToCart(User user, int productId) throws Exception;

    Object reduceInCart(User user, int productId) throws Exception;

    Object overViewCart(User user) throws Exception;

    Object createOrder(User user, OrderRequest orderRequest) throws Exception;

    Object viewAllOrder(User user) throws Exception;

    Object confirmOrder(User user, Integer orderId) throws Exception;

    Object rejectOrder(User user, Integer orderId) throws Exception;

    Object changeInfoUser(User user, UserRequest userRequest) throws Exception;
}
