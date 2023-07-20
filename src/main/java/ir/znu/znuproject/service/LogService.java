package ir.znu.znuproject.service;

import ir.znu.znuproject.dto.LogDTO;
import ir.znu.znuproject.dto.UserDTO;
import ir.znu.znuproject.entity.Log;
import ir.znu.znuproject.entity.User;
import ir.znu.znuproject.repository.LogRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public ResponseEntity<Response<Log>> getList() {
        Response response = new Response();
        Map map = new HashMap<String, List<UserDTO>>();
        List<LogDTO> logs = logRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
        try {
            map.put("logs", logs);
            response.setData(map);
            response.setStatus(200);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage("Error occurred!");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> saveNewLog(String content) {
        Log savingLog = new Log();
        Response response = new Response();

        savingLog.setContent(content);
        savingLog.setDate(LocalDate.now());

        try {
            logRepository.save(savingLog);
            response.setStatus(200);
            response.setMessage("New record added!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage(e.toString());
            return ResponseEntity.internalServerError().body(response);
        }

    }

    private LogDTO convertEntityToDTO(Log log) {
        LogDTO dto = new LogDTO();
        dto.setContent(log.getContent());
        dto.setDate(log.getDate());
        return dto;
    }
}
