package ir.znu.znuproject.service;

import ir.znu.znuproject.command.log.LogCreateCommand;
import ir.znu.znuproject.dto.log.LogDTO;
import ir.znu.znuproject.dto.log.LogDtoMapper;
import ir.znu.znuproject.dto.log.LogListDTO;
import ir.znu.znuproject.repository.LogRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {
    private final LogRepository logRepository;
    private final LogDtoMapper logDtoMapper;
    private final Response response;

    @Autowired
    public LogService(LogRepository logRepository, LogDtoMapper logDtoMapper, Response response) {
        this.logRepository = logRepository;
        this.logDtoMapper = logDtoMapper;
        this.response = response;
    }

    public ResponseEntity<Response<LogListDTO>> getAllLogs() {
        List<LogDTO> logs = logRepository.findAll().stream().map(logDtoMapper).collect(Collectors.toList());
        try {
            LogListDTO logListDto = LogListDTO.builder()
                    .logs(logs)
                    .rowCount(logs.size())
                    .build();
            response.setData(logListDto);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred!");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> save(LogCreateCommand command) {
        try {
            logRepository.save(command.toEntity());
            response.setMessage("New record saved!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.toString());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> deleteAll() {
        logRepository.deleteAll();
        response.setMessage("log history cleared.");
        return ResponseEntity.ok(response);
    }

}
