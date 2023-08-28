package ir.znu.znuproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "temperatureGoal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TemperatureGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordID;

    private Integer goal;

    @Column(nullable = false)
    private Long date;

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }
}
