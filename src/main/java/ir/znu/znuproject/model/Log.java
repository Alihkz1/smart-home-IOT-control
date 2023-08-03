package ir.znu.znuproject.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "logs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long date;


    public Log(String content, Long date) {
        this.content = content;
        this.date = date;
    }

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }
}
