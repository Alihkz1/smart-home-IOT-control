package ir.znu.znuproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@Builder
public class LogListDTO {
    private List<LogDTO> logs;
    private Integer rowCount;

}
