package ir.znu.znuproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "temperature")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecordID;

    private Integer Temperature;

    @Column(nullable = false)
    private Long date;

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }
}
