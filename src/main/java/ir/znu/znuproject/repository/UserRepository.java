package ir.znu.znuproject.repository;

import ir.znu.znuproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    @Query(value = "select * from users where username = :username RETURNING *", nativeQuery = true)
    User login(String username);

    @Modifying
    @Transactional
    @Query(value = "update users set password = :password where ID = :Id ", nativeQuery = true)
    void changePassword(Long Id, String password);

    @Modifying
    @Transactional
    @Query(value = "delete from users where username = :username", nativeQuery = true)
    void deleteByUsername(String username);

}
