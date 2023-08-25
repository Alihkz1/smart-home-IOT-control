package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.heater.HeaterChangeCommand;
import ir.znu.znuproject.enums.SWITCH;
import ir.znu.znuproject.service.HeaterService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public String status() {
        return heaterService.status();
    }

    @DeleteMapping(path = "deleteall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll() {
        return heaterService.deleteAll();
    }
}
