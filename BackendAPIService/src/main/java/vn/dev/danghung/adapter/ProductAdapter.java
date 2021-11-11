package vn.dev.danghung.adapter;

import org.springframework.stereotype.Component;
import vn.dev.danghung.entities.Product;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.model.request.ProductRequest;
import vn.dev.danghung.model.response.ProductResponse;

import java.util.Date;
import java.text.SimpleDateFormat;

@Component("productAdapter")
public class ProductAdapter implements EntitiesAdapter<ProductRequest, Product> {
    @Override
    public Product transform(ProductRequest productRequest) throws CommonException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = df.parse(productRequest.getDate());
        }catch (Exception e){
            throw new CommonException("Date format is failed", ErrorCode.DATE_TIME_INVALID);
        }
        Product product = new Product(
                productRequest.getName(),
                productRequest.getPrice(),
                productRequest.getQuantity(),
                productRequest.getImages(),
                productRequest.getDescription(),
                0,
                productRequest.getRam(),
                productRequest.getSsd(),
                productRequest.getDisplay(),
                productRequest.getWeight(),
                productRequest.getCpu(),
                productRequest.getSale(),
                productRequest.getSold(),
                productRequest.getDate()
        );
        return product;
    }

    public ProductResponse transform(Product product){
        ProductResponse productResponse = new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getImages(),
                product.getDes(),
                product.getRam(),
                product.getSsd(),
                product.getDisplay(),
                product.getWeight(),
                product.getCpu(),
                product.getSale(),
                product.getSold(),
                product.getDate()
        );
        return productResponse;
    }
}
