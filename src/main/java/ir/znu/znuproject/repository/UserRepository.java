package ir.znu.znuproject.repository;

import ir.znu.znuproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where username = :username and password = :password", nativeQuery = true)
    User login(String username, String password);
}
