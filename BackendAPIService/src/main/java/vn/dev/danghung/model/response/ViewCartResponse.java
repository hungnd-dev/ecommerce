package vn.dev.danghung.model.response;
import java.util.List;
public class ViewCartResponse {
    private List<CartDetailResponse> detail;
    private double amount;

    public ViewCartResponse() {
    }

    public ViewCartResponse(List<CartDetailResponse> detail, double amount) {
        this.detail = detail;
        this.amount = amount;
    }

    public List<CartDetailResponse> getDetail() {
        return detail;
    }

    public void setDetail(List<CartDetailResponse> detail) {
        this.detail = detail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
