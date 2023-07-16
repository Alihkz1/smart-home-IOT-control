package ir.znu.znuproject.User;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String username;
    private String password;
    private LocalDate expireDate;

    public User() {
    }

    public User(Long ID, String username, String password, LocalDate expireDate) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.expireDate = expireDate;
    }

    public User(String username, String password, LocalDate expireDate) {
        this.username = username;
        this.password = password;
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }

    public Long getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }
}
