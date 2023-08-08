package TubeNova.app.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateResponseDto {
    private String email;
    private String name;
    private String authority;




}