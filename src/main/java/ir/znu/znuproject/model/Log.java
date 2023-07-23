package ir.znu.znuproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

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
    private String date;


    public Log(String content, String date) {
        this.content = content;
        this.date = date;
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
