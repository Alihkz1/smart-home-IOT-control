package ir.znu.znuproject.service;

import ir.znu.znuproject.command.heater.HeaterChangeCommand;
import ir.znu.znuproject.enums.SWITCH;
import ir.znu.znuproject.model.Heater;
import ir.znu.znuproject.repository.HeaterRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HeaterService {
    private final HeaterRepository repository;
    private final Response response;

    @Autowired
    public HeaterService(HeaterRepository repository, Response response) {
        this.repository = repository;
        this.response = response;
    }

    public ResponseEntity<Response> change(HeaterChangeCommand command) {
        repository.save(command.toEntity());
        return ResponseEntity.ok(new Response());
    }

    public ResponseEntity<SWITCH> status() {
        Heater heater = repository.getStatus();
        response.setData(heater.getStatus());
        return ResponseEntity.ok(heater.getStatus());
    }

    public ResponseEntity<Response> deleteAll() {
        repository.deleteAll();
        response.setMessage("heater history cleared.");
        return ResponseEntity.ok(response);
    }
}
