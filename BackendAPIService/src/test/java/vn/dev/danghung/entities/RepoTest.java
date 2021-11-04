package vn.dev.danghung.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.dev.danghung.dao.*;

import java.util.ArrayList;
import java.util.List;
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
    private PasswordEncoder passwordEncoder;

    @Test
    void testBrandRepo() {
        int id = 5;
        System.out.println(brandRepo.findById(id).toString());
    }
    @Test
    void testProductRepo(){
        int id = 6;
//        List<Product> productList = new ArrayList<>();
//        productList = productRepo.findAll();
//        for(Product product: productList){
//            System.out.println(product.toString());
//        }
//        System.out.println(productRepo.findById(id).get().toString());
        List<Integer> idList = new ArrayList<>();
        idList.add(2);
        idList.add(5);
        idList.add(6);
        System.out.println(productRepo.findAllByIdIn(idList).size());
    }

    @Test
    void testUserRepo(){
        User user = userRepo.findById(6).get();
        System.out.println(user.toString());
        user.setUsername("hahahhahahah");
        userRepo.save(user);
        User userw = userRepo.findById(6).get();
        System.out.println(userw.toString());
    }

    @Test
    void testCartRepo(){
        int id = 6;
        Cart cart = cartRepo.findByUserIdIsAndOrderStateIs(6,0);

        System.out.println(cart);
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
//        int id = 6;
//        Order order = orderRepo.findById(id).get();
//        if(order != null){
//            System.out.println(order.toString());
//        }else{
//            System.out.println("No data in database");
//        }
        long fromDate = 1636017724685L;
        long toDate = fromDate + 151321;
        System.out.println(orderRepo.findAllByCreateAtBetween(fromDate,toDate).size());
    }


}