package ir.znu.znuproject.controller;

import ir.znu.znuproject.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/light")
public class LightController {
    private final LightService lightService;

    @Autowired
    public LightController(LightService lightService) {
        this.lightService = lightService;
    }
}
