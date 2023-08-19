package ir.znu.znuproject.service;

import ir.znu.znuproject.command.Temperature.TemperatureChangeCommand;
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
        repository.save(command.toEntity());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> value() {
        response.setData(repository.getValue());
        return ResponseEntity.ok(response);
    }
}
