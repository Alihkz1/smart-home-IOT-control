package ir.znu.znuproject.service;

import ir.znu.znuproject.command.LogAddCommand;
import ir.znu.znuproject.dto.LogDTO;
import ir.znu.znuproject.dto.LogDtoMapper;
import ir.znu.znuproject.model.Log;
import ir.znu.znuproject.repository.LogRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public ResponseEntity<Response> getAllLogs() {
        Response response = new Response();
        Map map = new HashMap<String, List<LogDTO>>();
        List<LogDTO> logs = logRepository.findAll().stream().map(logDtoMapper).collect(Collectors.toList());
        try {
            map.put("logs", logs);
            map.put("length", logs.size());
            response.setData(map);
            response.setSuccess(true);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred!");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> save(LogAddCommand command) {
        Response response = new Response();
        try {
            logRepository.save(command.toEntity());
            response.setSuccess(true);
            response.setMessage("New record saved!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.toString());
            return ResponseEntity.internalServerError().body(response);
        }

    }

}
