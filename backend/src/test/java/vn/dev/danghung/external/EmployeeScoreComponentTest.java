package vn.dev.danghung.external;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.dev.danghung.AppConfig;
import vn.dev.danghung.model.dto.EmployeeScoreDTO;
import vn.dev.danghung.service.EmployeeService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AppConfig.class})
public class EmployeeScoreComponentTest {

    @Autowired
    private EmployeeScoreComponent employeeScoreComponent;

    @Autowired
    private EmployeeDAO employeeDAO ;

    @Autowired
    private EmployeeService employeeService ;

    @Test
    public void getEmployeeScore() {
        EmployeeScoreDTO employeeScoreDTO = employeeScoreComponent.getEmployeeScore(1L);
        assertEquals(employeeScoreDTO.getStatus(), 0);
    }
}