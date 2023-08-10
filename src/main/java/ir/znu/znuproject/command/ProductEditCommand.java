package ir.znu.znuproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.znu.znuproject.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEditCommand {
    @JsonProperty("ID")
    private Long ID;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private Long price;
    @JsonProperty("off")
    private Long off;
    @JsonProperty("totalSold")
    private Long totalSold;
    @JsonProperty("amount")
    private Long amount;

    public Product toEntity() {
        return Product.builder()
                .ID(ID)
                .name(name)
                .description(description)
                .price(price)
                .off(off)
                .totalSold(totalSold)
                .amount(amount)
                .build();
    }

}
