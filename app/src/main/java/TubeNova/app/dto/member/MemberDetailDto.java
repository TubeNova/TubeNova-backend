package TubeNova.app.dto.member;

import lombok.Getter;

@Getter
public class MemberDetailDto {
    private Long id;
    private String name;
    private Long subscribeCount;
    private boolean isSubscribed;

    public MemberDetailDto(Long id, String name, Long subscribeCount) {
        this.id = id;
        this.name = name;
        this.subscribeCount = subscribeCount;
    }

    public MemberDetailDto(Long id, String name, Long subscribeCount, Boolean isSubscribed) {
        this.id = id;
        this.name = name;
        this.subscribeCount = subscribeCount;
        this.isSubscribed = isSubscribed;
    }
    public MemberDetailDto setSubscribed(boolean isSubscribed){
        this.isSubscribed = isSubscribed;
        return this;
    }
}
