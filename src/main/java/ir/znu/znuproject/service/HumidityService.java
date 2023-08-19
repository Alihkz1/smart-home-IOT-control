package ir.znu.znuproject.service;

import ir.znu.znuproject.command.Humidity.HumidityChangeCommand;
import ir.znu.znuproject.repository.HumidityRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HumidityService {
    private final HumidityRepository repository;
    private final Response response;

    @Autowired
    public HumidityService(HumidityRepository repository, Response response) {
        this.repository = repository;
        this.response = response;
    }

    public ResponseEntity<Response> change(HumidityChangeCommand command) {
        repository.save(command.toEntity());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> value() {
        response.setData(repository.getValue());
        return ResponseEntity.ok(response);
    }
}
