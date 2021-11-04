package vn.dev.danghung.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CartDetailRepoTest {
    @Autowired
    private CartDetailRepo cartDetailRepo;

    @Test
    void findAllByCartId() {
        System.out.println(cartDetailRepo.findAllByCartId(6).size());
    }
}