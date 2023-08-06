package TubeNova.app.dto.member;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private String name;
    private List<String> categories;
    private String originalPassword;
    private String updatedPassword;

}