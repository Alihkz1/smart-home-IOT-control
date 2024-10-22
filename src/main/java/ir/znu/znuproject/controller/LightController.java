package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.Light.ChangeIntensityCommand;
import ir.znu.znuproject.command.Light.LightChangeCommand;
import ir.znu.znuproject.service.LightService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/light")
@CrossOrigin(origins = "*")
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

    @GetMapping(path = "intensity")
    public Integer intensity() {
        return lightService.intensity();
    }

    @PostMapping(path = "changeIntensity")
    public ResponseEntity<Response> changeIntensity(@RequestBody ChangeIntensityCommand command) {
        return lightService.changeIntensity(command);
    }

    @GetMapping(path = "status")
    public String status() {
        return lightService.status();
    }

    @GetMapping(path = "level")
    public Integer level() {
        return lightService.level();
    }


    @DeleteMapping(path = "deleteall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll() {
        return lightService.deleteAll();
    }
}
