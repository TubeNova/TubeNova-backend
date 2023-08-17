package TubeNova.app.dto.member;

import lombok.Builder;

public class MemberDetailDto {
    private String id;
    private String name;
    private Long subscribeCount;

    @Builder
    public MemberDetailDto(String id, String name, Long subscribeCount) {
        this.id = id;
        this.name = name;
        this.subscribeCount = subscribeCount;
    }
}