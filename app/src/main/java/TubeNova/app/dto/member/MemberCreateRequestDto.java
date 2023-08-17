package TubeNova.app.dto.member;

import TubeNova.app.domain.Authority;
import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String name;
    private List<Category> categories;
    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .favoriteCategories(categories)
                .authority(Authority.ROLE_USER)
                .build();
    }


}