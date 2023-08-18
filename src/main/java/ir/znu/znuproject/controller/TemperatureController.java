package ir.znu.znuproject.controller;

import ir.znu.znuproject.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }
}
