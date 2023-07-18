package ir.znu.znuproject.Log;

import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    Response<Log> getLogList() {
        return logService.getList();
    }

    @PostMapping(path = "add")
    Response saveNewLog(@RequestBody(required = true) Log log) {
        return logService.saveNewLog(log.getContent());

    }
}
