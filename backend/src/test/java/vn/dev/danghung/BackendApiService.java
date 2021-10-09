package vn.dev.danghung;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.dev.danghung.model.request.SignRequest;
import vn.dev.danghung.service.SignActionService;
import vn.dev.danghung.service.SignActionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApiService {

    @Test
    public void contextLoads() {
        SignRequest signRequest = new SignRequest();
        signRequest.setPassword("buianhvan");
        signRequest.setUsername("danghung");
        SignActionServiceImpl signActionService = new SignActionServiceImpl();
        try {
            System.out.println(signActionService.getJwtTokenRing(signRequest).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
