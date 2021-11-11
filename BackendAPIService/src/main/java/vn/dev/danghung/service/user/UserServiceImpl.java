package vn.dev.danghung.service.user;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vn.dev.danghung.adapter.UserAdapter;
import vn.dev.danghung.dao.*;
import vn.dev.danghung.entities.*;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.model.request.OrderRequest;
import vn.dev.danghung.model.request.UserRequest;
import vn.dev.danghung.model.response.CartDetailResponse;
import vn.dev.danghung.model.response.ViewCartResponse;
import vn.dev.danghung.policy.UserRule;
import vn.dev.danghung.security.config.JwtTokenUtil;
import vn.dev.danghung.service.AbstractService;
import vn.dev.danghung.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class UserServiceImpl extends AbstractService implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartDetailRepo cartDetailRepo;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRule userRule;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    @Qualifier("userAdapter")
    private UserAdapter userAdapter;

    @Override
    public Object addToCart(User user, int productId) throws Exception {
        try{
            Cart cart = cartRepo.findByUserIdIsAndOrderStateIs(user.getId(),0);
            double price = productRepo.findById(productId).get().getPrice();
            cart.setAmount(cart.getAmount() + price);
            CartDetail cartDetail = cartDetailRepo.findByCartIdIsAndProductIdIs(cart.getId(),productId);
            if(cartDetail == null){
                cartDetail = new CartDetail();
                cartDetail.setCartId(cart.getId());
                cartDetail.setProductId(productId);
                cartDetail.setQuantity(1);
            }else{
                cartDetail.setQuantity(cartDetail.getQuantity()+1);
            }
            cartDetailRepo.save(cartDetail);
            cartRepo.save(cart);
        }catch (Exception e){
            eLogger.error("error when add to cart, reason {}", e.getMessage());
            throw new CommonException("error when add to cart", ErrorCode.CART_ADD);
        }
        return new ArrayList<>();
    }

    @Override
    public Object reduceInCart(User user, int productId) throws Exception {
        try{
            Cart cart = cartRepo.findByUserIdIsAndOrderStateIs(user.getId(),0);
            CartDetail cartDetail = cartDetailRepo.findByCartIdIsAndProductIdIs(cart.getId(),productId);
            if(cartDetail == null){
                throw new CommonException("dont exist product in cart to reduce", ErrorCode.CART_REDUCE);
            }else{
                cartDetail.setQuantity(cartDetail.getQuantity() - 1);
            }
            double price = productRepo.findById(productId).get().getPrice();
            cart.setAmount(cart.getAmount() - price);
            cartDetailRepo.save(cartDetail);
            cartRepo.save(cart);
        }catch (Exception e){
            eLogger.error("error when reduce product in cart, reason {}", e.getMessage());
            throw new CommonException("error when reduce product in cart", ErrorCode.CART_REDUCE);
        }
        return new ArrayList<>();
    }

    @Override
    public Object overViewCart(User user) throws Exception {
        List<CartDetail> cartDetails = new ArrayList<>();
        List<CartDetailResponse> cartDetailResponses = new ArrayList<>();
        double amount;
        try{
            Cart cart = cartRepo.findByUserIdIsAndOrderStateIs(user.getId(),0);
            amount = cart.getAmount();
            cartDetails = cartDetailRepo.findAllByCartId(cart.getId());
            List<Integer> idList = cartDetails.stream()
                    .map(e -> e.getProductId())
                    .collect(Collectors.toList());
            Map<Integer,Product> products = productRepo.findAllByIdIn(idList)
                    .stream()
                    .collect(Collectors.toMap(Product::getId, Function.identity()));
            for(CartDetail cartDetail:cartDetails){
                CartDetailResponse cartDetailResponse = new CartDetailResponse();
                Product product = products.get(cartDetail.getProductId());
                cartDetailResponse.setName(product.getName());
                cartDetailResponse.setPrice(product.getPrice());
                cartDetailResponse.setImage(product.getImages());
                cartDetailResponse.setSale(product.getSale());
                cartDetailResponse.setQuantity(cartDetail.getQuantity());
                cartDetailResponse.setTotal(cartDetail.getQuantity()* product.getPrice());
                cartDetailResponses.add(cartDetailResponse);
            }
        }catch (Exception e){
            eLogger.error("error when view all product in cart, reason {}", e.getMessage());
            throw new CommonException("error when view all product in cart", ErrorCode.CART_OVERVIEW);
        }
        ViewCartResponse viewCartResponse = new ViewCartResponse(cartDetailResponses,amount);
        return viewCartResponse;
    }

    @Override
    public Object createOrder(User user, OrderRequest orderRequest) throws Exception {
        Order order = new Order();
        try{
            Cart cart = cartRepo.findByUserIdIsAndOrderStateIs(user.getId(),0);
            order.setCartId(cart.getId());
            order.setUserId(user.getId());
            order.setAddress(orderRequest.getAddress());
            order.setDeliveryType(orderRequest.getDeliveryType());
            order.setPhoneReceive(orderRequest.getPhoneReceive());
            order.setCreateAt(System.currentTimeMillis());
            order.setState(0);
            order.setAmount(cart.getAmount());
            orderRepo.save(order);
            cart.setOrderState(1);
            cartRepo.save(cart);
            //create new cart for this user
            Cart cartN = new Cart();
            cartN.setUserId(user.getId());
            cartN.setOrderState(0);
            cartRepo.save(cartN);
        }catch (Exception e){
            eLogger.error("error when create order, reason {}", e.getMessage());
            throw new CommonException("error when create order", ErrorCode.ORDER_USER_CREATE);
        }

        return order;
    }

    @Override
    public Object viewAllOrder(User user) throws Exception {
        List<Order> orders = new ArrayList<>();
        try{
            orders = orderRepo.findAllByUserId(user.getId());
        }catch (Exception e){
            eLogger.error("error when get all order, reason {}", e.getMessage());
            throw new CommonException("error when get all order", ErrorCode.ORDER_USER_ALL);
        }
        return orders;
    }

    @Override
    public Object changeInfoUser(User user, UserRequest userRequest) throws Exception {
        if(!CommonUtils.checkEmpty(userRequest.getFullname())){
            userRule.verifyFullName(userRequest);
            user.setFullname(userRequest.getFullname());
        }
        if(!CommonUtils.checkEmpty(userRequest.getTelephone())){
            userRule.verifyPhoneNumber(userRequest);
            user.setTelephone(userRequest.getTelephone());
        }
        if(!CommonUtils.checkEmpty(userRequest.getPassword())){
            userRule.verifyPassword(userRequest);
            user.setPassword(userRequest.getPassword());
        }
        try{
            userRepo.save(user);
        }catch (Exception e){
            eLogger.error("error when change info user, reason {}", e.getMessage());
            throw new CommonException("error when change info user", ErrorCode.USER_CHANGE);
        }
        return userAdapter.transform(user);
    }
}
