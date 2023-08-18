package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long> {
}
