package ir.znu.znuproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LogDTO {
    private String content;
    private LocalDate date;
}
