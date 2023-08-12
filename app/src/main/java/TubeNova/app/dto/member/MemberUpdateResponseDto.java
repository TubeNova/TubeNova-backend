package TubeNova.app.dto.member;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateResponseDto {
    private String email;
    private String name;
    private List<String> categories;
}
