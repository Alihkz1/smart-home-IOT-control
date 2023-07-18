package ir.znu.znuproject.Log;

import ir.znu.znuproject.User.User;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Response<Log> getList() {
        Map map = new HashMap<String, List<User>>();
        map.put("logs",logRepository.findAll());
        Response response = new Response();
        response.setData(map);
        return response;
    }

    public Response addLog(String content) {
        Log savingLog = new Log();
        savingLog.setContent(content);
        savingLog.setDate(LocalDate.now());
        Response response = new Response();
        try {
            logRepository.save(savingLog);
            response.setStatus(200);
            response.setMessage("New record added!");
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage(e.toString());

        }
        return response;
    }
}
