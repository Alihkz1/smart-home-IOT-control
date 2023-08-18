package ir.znu.znuproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "heater")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Heater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecordID;

    private String Status;

    @Column(nullable = false)
    private Long date;

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }


}
