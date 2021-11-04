package vn.dev.danghung.service.guest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import vn.dev.danghung.entities.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceImplTest {
    @Autowired
    @Qualifier("guestService")
    private GuestService guestService;

    @Test
    void getAllProduct() {
        System.out.println(guestService == null);
//        List<Product> arrayList = new ArrayList<>();
//        try {
//            arrayList = guestService.getAllProduct();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(arrayList != null){
//            System.out.println(arrayList.size());
//        }
    }

    @Test
    void findProduct() {
    }
}