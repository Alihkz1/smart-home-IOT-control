package ir.znu.znuproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    private String content;
    private Long date;


    public Log(String content, Long date) {
        this.content = content;
        this.date = date;
    }

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }


    @Override
    public String toString() {
        return "Log{" +
                "ID=" + ID +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
