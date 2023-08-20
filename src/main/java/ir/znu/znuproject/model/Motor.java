package ir.znu.znuproject.model;

import ir.znu.znuproject.enums.SWITCH;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "motor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Motor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecordID;

    @Enumerated(EnumType.STRING)
    private SWITCH Status;
    @Column(nullable = false)
    private Long date;

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }
}
