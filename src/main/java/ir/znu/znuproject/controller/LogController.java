package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.log.LogCreateCommand;
import ir.znu.znuproject.dto.log.LogListDTO;
import ir.znu.znuproject.service.LogService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<LogListDTO>> list() {
        return logService.getAllLogs();
    }

    @PostMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestBody(required = true) LogCreateCommand command) {
        return logService.save(command);
    }

    @DeleteMapping(path = "deleteall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll(){
        return logService.deleteAll();
    }
}
