package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.Heater.HeaterChangeCommand;
import ir.znu.znuproject.service.HeaterService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/heater")
public class HeaterController {

    private final HeaterService heaterService;

    @Autowired
    public HeaterController(HeaterService heaterService) {
        this.heaterService = heaterService;
    }

    @PostMapping(path = "change")
    public ResponseEntity<Response> change(@RequestBody HeaterChangeCommand command) {
        return heaterService.change(command);

    }

    @GetMapping(path = "status")
    public ResponseEntity<Response> status() {
        return heaterService.status();
    }
}
