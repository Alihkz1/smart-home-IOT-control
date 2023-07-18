package ir.znu.znuproject.repository;

import ir.znu.znuproject.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
//    @Query(value = "select * from logs",nativeQuery = true)
//    getList();
}
