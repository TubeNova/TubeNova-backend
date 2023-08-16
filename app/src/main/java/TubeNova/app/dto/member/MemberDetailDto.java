package TubeNova.app.dto.member;

import lombok.Builder;

public class MemberDetailDto {
    String id;
    String name;
    String subscribeCount;

    @Builder
    public MemberDetailDto(String id, String name, String subscribeCount) {
        this.id = id;
        this.name = name;
        this.subscribeCount = subscribeCount;
    }
}
