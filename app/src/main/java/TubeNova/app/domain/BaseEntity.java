package TubeNova.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
