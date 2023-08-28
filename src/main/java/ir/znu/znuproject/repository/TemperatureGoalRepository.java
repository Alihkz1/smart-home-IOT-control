package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Temperature;
import ir.znu.znuproject.model.TemperatureGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemperatureGoalRepository  extends JpaRepository<TemperatureGoal, Long> {
    @Query(value = "SELECT * FROM temperature_goal ORDER BY RecordID DESC LIMIT 1", nativeQuery = true)
    public TemperatureGoal getLast();
}
