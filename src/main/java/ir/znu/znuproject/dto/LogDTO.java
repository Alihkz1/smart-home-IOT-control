package ir.znu.znuproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LogDTO {
    @JsonProperty("content")
    private String content;
    private LocalDate date;
}
