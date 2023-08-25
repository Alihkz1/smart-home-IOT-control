package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.Temperature.TemperatureChangeCommand;
import ir.znu.znuproject.service.TemperatureService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping(path = "change")
    public ResponseEntity<Response> change(@RequestBody TemperatureChangeCommand command) {
        return temperatureService.change(command);
    }

    @GetMapping(path = "value")
    public String value() {
        return temperatureService.value();
    }

    @DeleteMapping(path = "deleteall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll() {
        return temperatureService.deleteAll();
    }
}
