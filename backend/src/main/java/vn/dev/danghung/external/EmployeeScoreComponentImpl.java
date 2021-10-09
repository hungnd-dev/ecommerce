package vn.dev.danghung.external;

import net.jodah.failsafe.Failsafe;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import vn.dev.danghung.model.dto.EmployeeScoreDTO;
import vn.dev.danghung.external.task.EmployeeScoreGetterTask;

import java.util.concurrent.CompletableFuture;

@Component
public class EmployeeScoreComponentImpl extends AbstractExternalComponent implements EmployeeScoreComponent {

    @Override
    public EmployeeScoreDTO getEmployeeScore(long employeeID) {
        try {
            String apiResult = Failsafe.with(retryPolicy).get(
                    () -> new EmployeeScoreGetterTask(employeeID).performTask()
            );
            return gson.fromJson(apiResult, EmployeeScoreDTO.class);
        } catch (Exception e) {
            eLogger.error("employee score api call error: " + e.getMessage());
            return createDefaultResponse();
        }
    }

    @Override
    @Async("ConcurrentTaskExecutor")
    public CompletableFuture<EmployeeScoreDTO> getEmployeeScoreAsync(long employeeID) {
        EmployeeScoreDTO employeeScoreDTO = getEmployeeScore(employeeID);
        return CompletableFuture.completedFuture(employeeScoreDTO);
    }

    private EmployeeScoreDTO createDefaultResponse() {
        EmployeeScoreDTO defaultDTO = new EmployeeScoreDTO(
                0,                                  // <-- mark request is failed
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        defaultDTO.setScoreDetail(new Object());
        return defaultDTO;
    }

}
