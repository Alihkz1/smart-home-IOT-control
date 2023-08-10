package ir.znu.znuproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddEditCommand {
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

}
