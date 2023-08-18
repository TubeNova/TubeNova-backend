package TubeNova.app.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDetailDto {
    private Long id;
    private String name;
    private Long subscribeCount;


}
