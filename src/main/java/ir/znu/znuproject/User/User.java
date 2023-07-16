package ir.znu.znuproject.User;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

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
}
