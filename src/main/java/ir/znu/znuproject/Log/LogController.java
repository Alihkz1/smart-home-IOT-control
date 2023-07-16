package ir.znu.znuproject.Log;

import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    Response<Log> getList() {
        Response response = new Response<Log>();
        response.setData(logService.getList());
        return response;
    }

    @PostMapping(path = "add")
    Response addLog(@RequestBody(required = true) Log log){
        return logService.addLog(log.getContent());

    }
}
