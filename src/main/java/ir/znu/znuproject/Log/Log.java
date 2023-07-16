package ir.znu.znuproject.Log;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    private String content;
    private LocalDate date;

    public Log() {
    }

    public Log(Long ID, String content, LocalDate date) {
        this.ID = ID;
        this.content = content;
        this.date = date;
    }

    public Log(String content, LocalDate date) {
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
