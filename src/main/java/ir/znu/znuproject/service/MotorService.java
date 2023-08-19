package ir.znu.znuproject.service;

import ir.znu.znuproject.command.motor.MotorChangeCommand;
import ir.znu.znuproject.repository.MotorRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MotorService {

    private final MotorRepository repository;
    private final Response response;

    @Autowired
    public MotorService(MotorRepository repository, Response response) {
        this.repository = repository;
        this.response = response;
    }

    public ResponseEntity<Response> change(MotorChangeCommand command) {
        repository.save(command.toEntity());
        return ResponseEntity.ok(new Response());
    }

    public ResponseEntity<Response> status() {
        response.setData(repository.getStatus());
        return ResponseEntity.ok(response);
    }
}
