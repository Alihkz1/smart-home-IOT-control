package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.motor.MotorChangeCommand;
import ir.znu.znuproject.enums.SWITCH;
import ir.znu.znuproject.service.MotorService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/motor")
public class MotorController {
    private final MotorService motorService;

    @Autowired
    public MotorController(MotorService motorService) {
        this.motorService = motorService;
    }

    @PostMapping(path = "change")
    public ResponseEntity<Response> change(@RequestBody MotorChangeCommand command) {
        return motorService.change(command);

    }

    @GetMapping(path = "status")
    public String status() {
        return motorService.status();
    }

    @DeleteMapping(path = "deleteall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll() {
        return motorService.deleteAll();
    }
}
