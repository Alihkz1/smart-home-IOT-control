package ir.znu.znuproject.controller;

import ir.znu.znuproject.entity.Log;
import ir.znu.znuproject.service.LogService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<Response<Log>> getLogList() {
        return logService.getList();
    }

    @PostMapping(path = "add")
    ResponseEntity<Response> saveNewLog(@RequestBody(required = true) Log log) {
        return logService.saveNewLog(log.getContent());

    }
}
