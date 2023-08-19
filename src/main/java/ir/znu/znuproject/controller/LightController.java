package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.Light.LightChangeCommand;
import ir.znu.znuproject.service.LightService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/light")
public class LightController {
    private final LightService lightService;

    @Autowired
    public LightController(LightService lightService) {
        this.lightService = lightService;
    }

    @PostMapping(path = "change")
    public ResponseEntity<Response> change(@RequestBody LightChangeCommand command) {
        return lightService.change(command);
    }

    @GetMapping(path = "status")
    public ResponseEntity<Response> status() {
        return lightService.status();
    }
}
