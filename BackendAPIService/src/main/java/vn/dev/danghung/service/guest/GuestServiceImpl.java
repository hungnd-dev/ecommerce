package vn.dev.danghung.service.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.dev.danghung.dao.BrandRepo;
import vn.dev.danghung.dao.ProductRepo;
import vn.dev.danghung.entities.Brand;
import vn.dev.danghung.entities.Product;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
@Service
public class GuestServiceImpl extends AbstractService implements GuestService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private BrandRepo brandRepo;

    @Override
    public List<Product> getAllProduct() throws Exception {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepo.findAll();
            return productList;
        }catch (Exception e){
            eLogger.error("error when find all product, reason {}", e.getMessage());
            throw new CommonException("error when find all product", ErrorCode.BRAND_FIND_ALL);
        }
    }

    @Override
    public List<Brand> getAllBrand() throws Exception {
        List<Brand> brandList = new ArrayList<>();
        try{
            brandList = brandRepo.findAll();
            return brandList;
        }catch (Exception e){
            eLogger.error("error when find all brand, reason {}", e.getMessage());
            throw new CommonException("error when find all brand", ErrorCode.BRAND_FIND_ALL);
        }
    }

    @Override
    public List<Product> findProduct(String searchKey) throws Exception {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepo.findAllByDesContains(searchKey);
        }catch (Exception e){
            eLogger.error("error when find product with search key, reason: {}", e.getMessage());
            throw new CommonException("error when find product", ErrorCode.PRODUCT_FIND);
        }
        return productList;
    }
}
