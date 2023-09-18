package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    @Query(value = "SELECT * FROM logs ORDER BY ID DESC", nativeQuery = true)
    public List<Log> getAll();
}
