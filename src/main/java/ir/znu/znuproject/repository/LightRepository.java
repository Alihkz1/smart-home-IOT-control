package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LightRepository extends JpaRepository<Light, Long> {
}
