package ir.znu.znuproject.service;

import ir.znu.znuproject.command.Temperature.TemperatureChangeCommand;
import ir.znu.znuproject.command.heater.HeaterChangeCommand;
import ir.znu.znuproject.command.motor.MotorChangeCommand;
import ir.znu.znuproject.enums.SWITCH;
import ir.znu.znuproject.model.Temperature;
import ir.znu.znuproject.repository.HeaterRepository;
import ir.znu.znuproject.repository.MotorRepository;
import ir.znu.znuproject.repository.TemperatureRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
    private final TemperatureRepository repository;
    private final MotorService motorService;
    private final HeaterService heaterService;
    private final Response response;

    @Autowired
    public TemperatureService(
            Response response,
            MotorService motorService,
            HeaterService heaterService,
            TemperatureRepository repository
            ) {
        this.response = response;
        this.repository = repository;
        this.motorService = motorService;
        this.heaterService = heaterService;
    }

    public ResponseEntity<Response> change(TemperatureChangeCommand command) {
        Temperature lastRecord = repository.getLast();
        MotorChangeCommand motorChangeCommand = new MotorChangeCommand();
        HeaterChangeCommand heaterChangeCommand = new HeaterChangeCommand();
        if (Integer.parseInt(lastRecord.getTemperature()) >= Integer.parseInt(command.getTemperature())) {
            motorChangeCommand.setStatus(SWITCH.ON);
            heaterChangeCommand.setStatus(SWITCH.OFF);
            motorService.change(motorChangeCommand);
            heaterService.change(heaterChangeCommand);
        }
        if (Integer.parseInt(lastRecord.getTemperature()) <= Integer.parseInt(command.getTemperature())) {
            motorChangeCommand.setStatus(SWITCH.OFF);
            heaterChangeCommand.setStatus(SWITCH.ON);
            motorService.change(motorChangeCommand);
            heaterService.change(heaterChangeCommand);
        }
        repository.save(Temperature.builder().Temperature(command.getTemperature()).build());
        return ResponseEntity.ok(new Response());
    }

    public String value() {
        Temperature temperature = repository.getLast();
        response.setData(temperature.getTemperature());
        return temperature.getTemperature();
    }

    public ResponseEntity<Response> deleteAll() {
        repository.deleteAll();
        response.setMessage("temperature list cleared.");
        return ResponseEntity.ok(response);
    }
}
