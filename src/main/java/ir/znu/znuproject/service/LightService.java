package ir.znu.znuproject.service;

import ir.znu.znuproject.command.Light.ChangeIntensityCommand;
import ir.znu.znuproject.command.Light.LightChangeCommand;
import ir.znu.znuproject.enums.SWITCH;
import ir.znu.znuproject.model.Light;
import ir.znu.znuproject.model.Log;
import ir.znu.znuproject.repository.LightRepository;
import ir.znu.znuproject.repository.LogRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LightService {

    private final LightRepository repository;
    private final LogRepository logRepository;
    private final Response response;

    @Autowired
    public LightService(LightRepository repository, Response response, LogRepository logRepository) {
        this.repository = repository;
        this.response = response;
        this.logRepository = logRepository;
    }

    public ResponseEntity<Response> change(LightChangeCommand command) {
        Log log = new Log();
        if (command.getStatus().equals(SWITCH.OFF)) {
            log.setContent("Lights Turned OFF.");
        } else if (command.getStatus().equals(SWITCH.ON)) {
            log.setContent("Lights Turned On.");
        }
        logRepository.save(log);
        repository.save(command.toEntity());
        return ResponseEntity.ok(new Response());
    }

    public String status() {
        Light light = repository.getLast();
        return light.getStatus().toString();
    }

    public Integer level() {
        Light light = repository.getLast();
        return light.getLevel();
    }

    public Integer intensity() {
        Light light = repository.getLast();
        return light.getIntensity();
    }

    public ResponseEntity<Response> deleteAll() {
        repository.deleteAll();
        response.setMessage("light history cleared.");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> changeIntensity(ChangeIntensityCommand command) {
        Light lastRecord = repository.getLast();
        lastRecord.setIntensity(command.getIntensity());
        repository.save(lastRecord);
        return ResponseEntity.ok().build();
    }
}
