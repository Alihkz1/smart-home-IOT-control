package ir.znu.znuproject.service;

import ir.znu.znuproject.command.LogCreateCommand;
import ir.znu.znuproject.dto.LogDTO;
import ir.znu.znuproject.dto.LogDtoMapper;
import ir.znu.znuproject.dto.LogListDTO;
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

    @Autowired
    public LogService(LogRepository logRepository, LogDtoMapper logDtoMapper) {
        this.logRepository = logRepository;
        this.logDtoMapper = logDtoMapper;
    }

    public ResponseEntity<Response<LogListDTO>> getAllLogs() {
        Response response = new Response();
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
        Response response = new Response();
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
        Response response = new Response();
        logRepository.deleteAll();
        response.setMessage("log history cleared.");
        return ResponseEntity.ok(response);
    }

}
