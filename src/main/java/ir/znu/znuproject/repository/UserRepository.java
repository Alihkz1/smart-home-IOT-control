package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    @Query(value = "select * from users where username = :username", nativeQuery = true)
    User login(String username);

}
