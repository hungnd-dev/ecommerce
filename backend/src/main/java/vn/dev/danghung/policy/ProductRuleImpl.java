package vn.dev.danghung.policy;

import org.springframework.stereotype.Component;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.model.request.ProductRequest;
import vn.dev.danghung.utils.CommonUtils;

@Component
public class ProductRuleImpl extends AbstractRule implements ProductRule{
    @Override
    public void verify(ProductRequest productRequest) throws CommonException {
        //check empty string value
        if(CommonUtils.checkEmpty(productRequest.getName())){
            throw new CommonException("Name is not empty", ErrorCode.PRODUCT_NAME);
        }if(CommonUtils.checkEmpty(productRequest.getDescription())){
            throw new CommonException("Description is not empty", ErrorCode.PRODUCT_DES);
        }if(CommonUtils.checkEmpty(productRequest.getDisplay())){
            throw new CommonException("Display is not empty", ErrorCode.PRODUCT_DISPLAY);
        }if(CommonUtils.checkEmpty(productRequest.getCpu())){
            throw new CommonException("Name is not empty", ErrorCode.PRODUCT_CPU);
        }if(CommonUtils.checkEmpty(productRequest.getBrandName())){
            throw new CommonException("Brand name is not empty", ErrorCode.BRAND_ADD);
        }

        if(CommonUtils.checkEmpty(productRequest.getDate())){
            throw new CommonException("Date is not empty", ErrorCode.DATE_TIME_INVALID);
        }
        try{
            df.parse(productRequest.getDate());
        }catch (Exception e){
            throw new CommonException("Date is failed format ", ErrorCode.DATE_TIME_INVALID);
        }

    }
}
