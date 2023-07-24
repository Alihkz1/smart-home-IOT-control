package ir.znu.znuproject.controller;

import ir.znu.znuproject.dto.LogDTO;
import ir.znu.znuproject.model.Log;
import ir.znu.znuproject.service.LogService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "list")
    ResponseEntity<Response<Map<String, List<LogDTO>>>> getLogList() {
        return logService.getList();
    }

    @PostMapping(path = "add")
    ResponseEntity<Response> save(@RequestBody(required = true) Log log) {
        return logService.save(log.getContent());

    }
}
