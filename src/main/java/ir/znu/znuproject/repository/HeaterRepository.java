package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaterRepository extends JpaRepository<Heater, Long> {

    @Query(value = "SELECT Status from heater ORDER BY RecordID DESC LIMIT 1", nativeQuery = true)
    public Heater getStatus();
}
