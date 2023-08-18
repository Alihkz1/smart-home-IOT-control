package ir.znu.znuproject.service;

import ir.znu.znuproject.command.heater.HeaterChangeCommand;
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
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> status() {
        response.setData(repository.getStatus());
        return ResponseEntity.ok(response);
    }
}
