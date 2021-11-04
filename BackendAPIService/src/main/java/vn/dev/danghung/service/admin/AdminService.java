package vn.dev.danghung.service.admin;

public interface AdminService {
    Object blockUser(String username) throws Exception;

    Object unBlockUser(String username) throws Exception;

    Object viewAllUser() throws Exception;

    Object viewAllOrder() throws Exception;

    Object viewOrderUnpaid() throws Exception;

    Object confirmOrderPaid(Integer orderId) throws Exception;

    Object rejectOrder(Integer orderId) throws Exception;

    Object viewTotalMoneyAndOrder(String fromDate, String toDate) throws Exception;


}


