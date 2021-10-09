package vn.dev.danghung.external;

import vn.dev.danghung.model.dto.EmployeeScoreDTO;

import java.util.concurrent.CompletableFuture;

public interface EmployeeScoreComponent {

    /**
     * get employee score by calling external API
     *
     * @param employeeID employee id
     * @return data transfer object for employee score
     */
    public abstract EmployeeScoreDTO getEmployeeScore(long employeeID);

    /**
     * get employee score by calling external API in ASYNC mode
     *
     * @param employeeID employee id
     * @return data transfer object for employee score
     */
    public abstract CompletableFuture<EmployeeScoreDTO> getEmployeeScoreAsync(long employeeID);
}
