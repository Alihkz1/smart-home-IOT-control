package ir.znu.znuproject.service;

import ir.znu.znuproject.command.Temperature.TemperatureChangeCommand;
import ir.znu.znuproject.model.Temperature;
import ir.znu.znuproject.repository.TemperatureRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
    private final TemperatureRepository repository;
    private final Response response;

    @Autowired
    public TemperatureService(TemperatureRepository repository, Response response) {
        this.repository = repository;
        this.response = response;
    }

    public ResponseEntity<Response> change(TemperatureChangeCommand command) {
        repository.save(Temperature.builder().Temperature(command.getTemperature()).build());
        return ResponseEntity.ok(new Response());
    }

    public ResponseEntity<Response> value() {
        response.setData(repository.getValue());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> deleteAll() {
        repository.deleteAll();
        response.setMessage("temperature list cleared.");
        return ResponseEntity.ok(response);
    }
}
