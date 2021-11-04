package vn.dev.danghung.service.admin;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vn.dev.danghung.adapter.UserAdapter;
import vn.dev.danghung.dao.OrderRepo;
import vn.dev.danghung.dao.UserRepo;
import vn.dev.danghung.entities.Order;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.model.response.StatisticalResponse;
import vn.dev.danghung.model.response.UserResponse;
import vn.dev.danghung.policy.DateTimeRule;
import vn.dev.danghung.service.AbstractService;
import vn.dev.danghung.utils.CommonUtils;
import vn.dev.danghung.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl extends AbstractService implements AdminService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private DateTimeRule dateTimeRule;

    @Autowired
    @Qualifier("userAdapter")
    private UserAdapter userAdapter;

    @Override
    public Object blockUser(String username) throws Exception {
        if (CommonUtils.checkEmpty(username)) {
            throw new CommonException("Username is empty", ErrorCode.USERNAME_INVALID);
        }
        User user = new User();
        try {
            user = userRepo.findUserByUsername(username);
            user.setState(0);
            userRepo.save(user);
        } catch (Exception e) {
            eLogger.error("error when block, reason {}", e.getMessage());
            throw new CommonException("error when block", ErrorCode.ADMIN_BLOCK_USER);
        }
        return user;
    }

    @Override
    public Object unBlockUser(String username) throws Exception {
        if (CommonUtils.checkEmpty(username)) {
            throw new CommonException("Username is empty", ErrorCode.USERNAME_INVALID);
        }
        User user = new User();
        try {
            user = userRepo.findUserByUsername(username);
            user.setState(1);
            userRepo.save(user);
        } catch (Exception e) {
            eLogger.error("error when unblock, reason {}", e.getMessage());
            throw new CommonException("error when unblock", ErrorCode.ADMIN_UNBLOCK_USER);
        }
        return user;
    }

    @Override
    public Object viewAllUser() throws Exception {
        List<User> userList = new ArrayList<>();
        List<UserResponse> userResponseList = new ArrayList<>();
        try {
            userList = userRepo.findAllByRole("client");
            userResponseList = userList.stream()
                    .map(e -> userAdapter.transform(e))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            eLogger.error("error when view all user, reason {}", e.getMessage());
            throw new CommonException("error when view all user", ErrorCode.ADMIN_GET_USER);
        }
        return userResponseList;
    }

    @Override
    public Object viewAllOrder() throws Exception {
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderRepo.findAll();
        } catch (Exception e) {
            eLogger.error("error when view all order, reason {}", e.getMessage());
            throw new CommonException("error when view all order", ErrorCode.ADMIN_GET_ORDER);
        }
        return orderList;
    }

    @Override
    public Object viewOrderUnpaid() throws Exception {
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderRepo.findAllByStateIs(0);
        } catch (Exception e) {
            eLogger.error("error when view all order unpaid, reason {}", e.getMessage());
            throw new CommonException("error when view all order unpaid", ErrorCode.ADMIN_GET_ORDER);
        }
        return orderList;
    }

    @Override
    public Object confirmOrderPaid(Integer orderId) throws Exception {
        Order order = new Order();
        try {
            order = orderRepo.findById(orderId).get();
            order.setState(1);
            orderRepo.save(order);
        } catch (Exception e) {
            eLogger.error("error when confirm order paid, reason {}", e.getMessage());
            throw new CommonException("error when confirm order paid", ErrorCode.ADMIN_CONFIRM_ORDER);
        }
        return order;
    }

    @Override
    public Object rejectOrder(Integer orderId) throws Exception {
        Order order = new Order();
        try {
            order = orderRepo.findById(orderId).get();
            order.setState(-1);
            orderRepo.save(order);
        } catch (Exception e) {
            eLogger.error("error when reject order, reason {}", e.getMessage());
            throw new CommonException("error when reject order", ErrorCode.ADMIN_REJECT_ORDER);
        }
        return order;
    }

    @Override
    public Object viewTotalMoneyAndOrder(String fromDate, String toDate) throws Exception {
        dateTimeRule.verify(fromDate,toDate);
        String datePattern = "yyyy-MM-dd";
        StatisticalResponse statisticalResponse = new StatisticalResponse();
        long from = DateTimeUtils.getTimeInMilliSecs(fromDate, datePattern);
        long to = DateTimeUtils.getTimeInMilliSecs(toDate, datePattern) + 1000 * 60 * 60 * 24 - 1;
        try {
            List<Order> orderList = orderRepo.findAllByCreateAtBetween(from, to);

            long tOrder = orderList.size();
            long tOrderPaid = 0L;
            long tOrderUnpaid = 0L;
            long tOrderReject = 0L;
            double money = 0;
            for (Order order : orderList) {
                if (order.getState() == 0) {
                    tOrderUnpaid += 1;
                } else if (order.getState() == 1) {
                    tOrderPaid += 1;
                    money += order.getAmount();
                } else {
                    tOrderReject += 1;
                }
            }
            statisticalResponse.setOrderList(orderList);
            statisticalResponse.setTotalOrder(tOrder);
            statisticalResponse.setMoney(money);
            statisticalResponse.setTotalOrderPaid(tOrderPaid);
            statisticalResponse.setTotalOrderReject(tOrderReject);
            statisticalResponse.setTotalOrderUnpaid(tOrderUnpaid);
        } catch (Exception e) {
            eLogger.error("error when view total money in year, reason {}", e.getMessage());
            throw new CommonException("error when total money in year", ErrorCode.ADMIN_STATISTICAL);
        }
        return statisticalResponse;
    }

}
