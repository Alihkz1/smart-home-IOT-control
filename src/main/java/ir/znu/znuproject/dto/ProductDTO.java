package ir.znu.znuproject.dto;

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
