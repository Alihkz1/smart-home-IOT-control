package ir.znu.znuproject.model;

import ir.znu.znuproject.enums.SWITCH;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "light")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Light {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecordID;

    @Enumerated(EnumType.STRING)
    private SWITCH Status;

    private Integer Intensity;

    @Column
    private Integer Level;

    @Column(nullable = false)
    private Long date;

    @PrePersist
    public void init() {
        this.date = new Date().getTime();
    }
}
