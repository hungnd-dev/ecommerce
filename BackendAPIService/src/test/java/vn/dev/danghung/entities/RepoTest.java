package vn.dev.danghung.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.dev.danghung.dao.*;

import java.util.Optional;

@SpringBootTest
class RepoTest {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartDetailRepo cartDetailRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testBrandRepo() {
        int id = 5;
        System.out.println(brandRepo.findById(id).toString());
    }
    @Test
    void testProductRepo(){
        int id = 6;
        System.out.println(productRepo.findById(id).toString());
    }

    @Test
    void testUserRepo(){
        User user = new User();
        user.setUsername("sbfrdgsvfr");
        user.setPassword(passwordEncoder.encode("buianhvan"));
        user.setRole("client");
        user.setCreatedAt(System.currentTimeMillis());
        user.setTelephone("012345789");
        user.setState(1);
        user.setFullname("Diem Thi Bao An");
        userRepo.save(user);

    }

    @Test
    void testCartRepo(){
        int id = 6;
        Cart cart = cartRepo.findById(id).get();
        if (cart != null) {
            System.out.println(cart.toString());
        }else{
            System.out.println("No data in database");
        }
    }
    @Test
    void testCartDetailRepo(){
        int id = 6;
        Optional<CartDetail> cartDetail = cartDetailRepo.findById(id);
        if(cartDetail.isPresent()) {
            System.out.println(cartDetail.get().toString());
        }   else{
            System.out.println("No data in database");
        }
    }
    @Test
    void testOrderRepo(){
        int id = 6;
        Order order = orderRepo.findById(id).get();
        if(order != null){
            System.out.println(order.toString());
        }else{
            System.out.println("No data in database");
        }
    }
    @Test
    void testOrderDetailRepo(){
        int id = 6;
        OrderDetail orderDetail = orderDetailRepo.findById(id).get();
        if(orderDetail != null){
            System.out.println(orderDetail.toString());
        }else{
            System.out.println("No data in database");
        }
    }


}