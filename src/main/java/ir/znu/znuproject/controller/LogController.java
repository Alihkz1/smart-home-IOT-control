package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.LogCommand;
import ir.znu.znuproject.dto.LogDTO;
import ir.znu.znuproject.model.Log;
import ir.znu.znuproject.service.LogService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("api/v1/logs")
@CrossOrigin(origins = "*")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getLogList() {
        return logService.getAllLogs();
    }

    @PostMapping(path = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> save(@RequestBody(required = true) LogCommand command) {
        return logService.save(command);

    }
}
