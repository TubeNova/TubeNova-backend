package TubeNova.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass   //JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들을 칼럼으로 인식 시킴
@EntityListeners(AuditingEntityListener.class)  //Auditing 기능 포함
public class BaseEntity {
    @CreationTimestamp
    @CreatedDate    //생성될 때 자동 저장
    @Column(updatable = false)
    private LocalDateTime createdTime = LocalDateTime.now();

    @CreationTimestamp
    @LastModifiedDate       //수정될 때 자동 저장
    private LocalDateTime updatedTime = LocalDateTime.now();;
}
