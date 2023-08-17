package ir.znu.znuproject.dto.product;

import ir.znu.znuproject.dto.product.ProductDTO;
import ir.znu.znuproject.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDtoMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getID(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getOff(),
                product.getTotalSold(),
                product.getAmount()
        );
    }
}
