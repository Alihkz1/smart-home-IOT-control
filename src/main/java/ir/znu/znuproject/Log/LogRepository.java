package ir.znu.znuproject.Log;

import ir.znu.znuproject.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
//    @Query(value = "select * from logs",nativeQuery = true)
//    getList();
}
