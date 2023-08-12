package ir.znu.znuproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder

public class ProductListDto {
    private List<ProductDTO> products;
    private Integer rowCount;

}
