package TubeNova.app.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
