package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.Humidity.HumidityChangeCommand;
import ir.znu.znuproject.enums.SWITCH;
import ir.znu.znuproject.service.HumidityService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/humidity")
public class HumidityController {

    private final HumidityService humidityService;

    @Autowired
    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @PostMapping(path = "change")
    public ResponseEntity<Response> change(@RequestBody HumidityChangeCommand command) {
        return humidityService.change(command);
    }

    @GetMapping(path = "value")
    public String value() {
        return humidityService.value();
    }

    @DeleteMapping(path = "deleteall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll() {
        return humidityService.deleteAll();
    }
}
