package ir.znu.znuproject.Log;

import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> getList() {
        return logRepository.findAll();
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
