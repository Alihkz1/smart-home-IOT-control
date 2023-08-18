package ir.znu.znuproject.controller;

import ir.znu.znuproject.service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/motor")
public class MotorController {
    private final MotorService motorService;

    @Autowired
    public MotorController(MotorService motorService) {
        this.motorService = motorService;
    }
}
