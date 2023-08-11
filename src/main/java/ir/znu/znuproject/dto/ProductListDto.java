package ir.znu.znuproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder

@Getter
@Setter
public class ProductListDto {
    private List<ProductDTO> products;
    private Integer rowCount;

}
