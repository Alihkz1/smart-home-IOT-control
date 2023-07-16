package ir.znu.znuproject.Log;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    @JsonProperty("content")
    private String content;
    @JsonProperty("date")
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

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
