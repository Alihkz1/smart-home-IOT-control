package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Light;
import ir.znu.znuproject.model.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface LightRepository extends JpaRepository<Light, Long> {

    @Query(value = "SELECT * FROM light ORDER BY RecordID DESC LIMIT 1", nativeQuery = true)
    public Motor getStatus();
}
