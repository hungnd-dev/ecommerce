package vn.dev.danghung.service.admin;

import vn.dev.danghung.model.request.ProductRequest;

public interface AdminService {
    Object blockUser(String username) throws Exception;

    Object unBlockUser(String username) throws Exception;

    Object viewAllUser() throws Exception;

    Object viewAllOrder() throws Exception;

    Object viewOrderUnpaid() throws Exception;

    Object confirmOrderPaid(Integer orderId) throws Exception;

    Object rejectOrder(Integer orderId) throws Exception;

    Object viewTotalMoneyAndOrder(int month) throws Exception;

    Object addProduct(ProductRequest productRequest) throws Exception;

    Object updateProduct() throws Exception;

    Object addBrand(String brandName) throws Exception;


}


