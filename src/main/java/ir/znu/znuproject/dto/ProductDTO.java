package ir.znu.znuproject.dto;

import javax.persistence.Column;

public record ProductDTO(
        Long ID,
        String name,
        String description,
        Long price,
        Long off,
        Long totalSold
        , Long amount
) {
}
