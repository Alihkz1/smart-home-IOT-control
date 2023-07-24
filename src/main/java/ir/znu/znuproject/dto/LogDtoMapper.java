package ir.znu.znuproject.dto;

import ir.znu.znuproject.model.Log;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LogDtoMapper implements Function<Log, LogDTO> {
    @Override
    public LogDTO apply(Log log) {
        return new LogDTO(
                log.getContent(),
                log.getDate()
        );
    }
}
