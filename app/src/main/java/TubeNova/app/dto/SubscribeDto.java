package TubeNova.app.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubscribeDto {
    private Long callerId;
    private Long targetId;
    @Builder
    public SubscribeDto(Long callerId, Long targetId) {
        this.callerId = callerId;
        this.targetId = targetId;
    }
}