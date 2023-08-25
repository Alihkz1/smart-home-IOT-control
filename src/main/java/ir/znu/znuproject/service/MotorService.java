package ir.znu.znuproject.service;

import ir.znu.znuproject.command.motor.MotorChangeCommand;
import ir.znu.znuproject.enums.SWITCH;
import ir.znu.znuproject.model.Motor;
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

    public String status() {
        Motor motor = repository.getStatus();
        response.setData(motor.getStatus());
        return motor.getStatus().toString();
    }

    public ResponseEntity<Response> deleteAll() {
        repository.deleteAll();
        response.setMessage("motor activity list cleared.");
        return ResponseEntity.ok(response);
    }
}
