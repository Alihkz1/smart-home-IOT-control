package ir.znu.znuproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder

@Getter
@Setter
public class LogListDto {
    private List<LogDTO> logs;
    private Integer rowCount;

}
