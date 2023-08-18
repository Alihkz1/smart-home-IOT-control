package ir.znu.znuproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "humidity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Humidity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecordID;

    private String Humidity;

    @Column(nullable = false)
    private Long date;

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }
}
