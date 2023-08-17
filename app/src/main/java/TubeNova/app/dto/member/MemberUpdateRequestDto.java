package TubeNova.app.dto.member;

import TubeNova.app.domain.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private String name;
    private List<Category> categories;
    private String originalPassword;
    private String updatedPassword;

}