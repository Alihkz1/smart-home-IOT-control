package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long> {
    @Query(value = "SELECT * FROM motor ORDER BY RecordID DESC LIMIT 1", nativeQuery = true)
    public Motor getStatus();
}
