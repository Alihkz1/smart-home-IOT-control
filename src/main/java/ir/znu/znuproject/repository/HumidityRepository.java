package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Humidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {
    @Query(value = "SELECT * FROM humidity ORDER BY RecordID DESC LIMIT 1", nativeQuery = true)
    public Humidity getValue();
}
