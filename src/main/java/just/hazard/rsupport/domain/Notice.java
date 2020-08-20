package just.hazard.rsupport.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate startDate;

    @Column
    @UpdateTimestamp
    private LocalDate updateDate;

    @ManyToOne
    private Account user;
}
