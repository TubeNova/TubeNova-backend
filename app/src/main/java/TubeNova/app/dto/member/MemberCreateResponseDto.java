package TubeNova.app.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateResponseDto {
    private String email;
    private String name;
    private List<String> categories;
    private String authority;
}