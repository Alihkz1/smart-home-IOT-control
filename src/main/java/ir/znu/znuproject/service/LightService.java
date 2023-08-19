package ir.znu.znuproject.service;

import ir.znu.znuproject.command.Light.LightChangeCommand;
import ir.znu.znuproject.repository.LightRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LightService {

    private final LightRepository repository;
    private final Response response;

    @Autowired
    public LightService(LightRepository repository, Response response) {
        this.repository = repository;
        this.response = response;
    }

    public ResponseEntity<Response> change(LightChangeCommand command) {
        repository.save(command.toEntity());
        return ResponseEntity.ok(new Response());
    }

    public ResponseEntity<Response> status() {
        response.setData(repository.getStatus());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> deleteAll() {
        repository.deleteAll();
        response.setMessage("light history cleared.");
        return ResponseEntity.ok(response);
    }
}
