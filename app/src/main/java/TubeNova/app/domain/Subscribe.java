package TubeNova.app.domain;

import TubeNova.app.dto.SubscribeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Subscribe {
    @Id
    @Column(name ="subscribe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore                             //@ManyToOne의 Fetch 타입을 Lazy로 사용했을 때 나타나는 문제점, 비어있는 객체를 Serialize 하려다 에러가 발생
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "target_id")
    private Long targetId;

    @Builder
    public Subscribe(Member member, Long targetId) {
        this.member = member;
        this.targetId = targetId;
    }

    public SubscribeDto toSubscribeDto(){
        SubscribeDto subscribeDto = new SubscribeDto(member.getId(), targetId);
        return subscribeDto;
    }
}
