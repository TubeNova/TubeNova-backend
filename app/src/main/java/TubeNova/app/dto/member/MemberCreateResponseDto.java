package TubeNova.app.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberCreateResponseDto {
    private String email;
    private String name;
    private List<String> categories;
    private String authority;
    private Boolean isSubscribed;

    public MemberCreateResponseDto(String email, String name, List<String> categories, String authority) {
        this.email = email;
        this.name = name;
        this.categories = categories;
        this.authority = authority;
    }
}