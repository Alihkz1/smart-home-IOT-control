package ir.znu.znuproject.controller;

import ir.znu.znuproject.service.HumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/humidity")
public class HumidityController {

    private final HumidityService humidityService;

    @Autowired
    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

}
