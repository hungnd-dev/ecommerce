package vn.dev.danghung.model.response;

import vn.dev.danghung.entities.Order;
import java.util.List;
public class StatisticalResponse {
    private double money;
    private long totalOrder;
    private long totalOrderPaid;
    private long totalOrderUnpaid;
    private long totalOrderReject;
    private List<Order> orderList;

    public StatisticalResponse() {
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    public long getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(long totalOrder) {
        this.totalOrder = totalOrder;
    }

    public long getTotalOrderPaid() {
        return totalOrderPaid;
    }

    public void setTotalOrderPaid(long totalOrderPaid) {
        this.totalOrderPaid = totalOrderPaid;
    }

    public long getTotalOrderUnpaid() {
        return totalOrderUnpaid;
    }

    public void setTotalOrderUnpaid(long totalOrderUnpaid) {
        this.totalOrderUnpaid = totalOrderUnpaid;
    }

    public long getTotalOrderReject() {
        return totalOrderReject;
    }

    public void setTotalOrderReject(long totalOrderReject) {
        this.totalOrderReject = totalOrderReject;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
