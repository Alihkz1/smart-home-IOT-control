package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    @Query(value = "SELECT * FROM temperature ORDER BY RecordID DESC LIMIT 1", nativeQuery = true)
    public Temperature getValue();
}
