package spring.sse;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@DynamicUpdate
@Getter
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Long number;

    public void addNumber() {
        this.number += 1;
    }
}
