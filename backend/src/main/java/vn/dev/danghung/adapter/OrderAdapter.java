package vn.dev.danghung.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vn.dev.danghung.dao.UserRepo;
import vn.dev.danghung.entities.Order;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.model.response.OrderResponse;
import vn.dev.danghung.utils.DateTimeUtils;
@Component("orderAdapter")
public class OrderAdapter implements EntitiesAdapter<Order, OrderResponse> {
    @Autowired
    private UserRepo userRepo;

    @Override
    public OrderResponse transform(Order order) throws CommonException {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setName(order.getName());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setAmount(order.getAmount());
        orderResponse.setCreateAt(DateTimeUtils.generateTime(order.getCreateAt()));
        orderResponse.setDeliveryType(order.getDeliveryType());
        orderResponse.setId(order.getId());
        orderResponse.setState(order.getState());
        orderResponse.setPhoneReceive(order.getPhoneReceive());
        return orderResponse;
    }
}
