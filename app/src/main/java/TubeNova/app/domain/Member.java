package TubeNova.app.domain;

import TubeNova.app.dto.member.MemberCreateResponseDto;
import TubeNova.app.dto.member.MemberDetailDto;
import TubeNova.app.dto.member.MemberUpdateResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @JsonProperty
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    @JsonProperty
    @Column(nullable = false)
    private String name;
    @JsonProperty
    @Column
    private Long subscribeCount = 0L;
    @Column(name ="favorite_category")
    private List<Category> favoriteCategory;

    @Builder
    public Member(String email, String password, String name, List<Category> favoriteCategories, Authority authority){
        this.email = email;
        this.password = password;
        this.name = name;
        this.favoriteCategory = favoriteCategories;
        this.authority = authority;
    }
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviewList =new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Subscribe> subscribes = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
    public void subscribeCountIncrease(){
        subscribeCount++;
    }
    public void subscribeCountDecrease(){
        subscribeCount--;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFavoriteCategory(List<Category> favoriteCategory) {
        this.favoriteCategory = favoriteCategory;
    }

    public String getEmail() {
        return email;
    }
    public List<Category> getCategories(){
        return favoriteCategory;
    }

    public boolean isPassword(String password){

        if(this.password.equals(password)){
            return true;
        }
        return false;
    }

    public static MemberCreateResponseDto of(Member member) {
        return new MemberCreateResponseDto(member.email, member.name, Category.toStringList(member.favoriteCategory), member.authority.toString());
    }

    public MemberDetailDto memberToDetailDto(){
        return new MemberDetailDto(id, name, subscribeCount);
    }

    public static MemberUpdateResponseDto memberToUpdateResponseDto(Member member){
        return new MemberUpdateResponseDto(member.email, member.name, Category.toStringList(member.favoriteCategory));
    }
    public static Page<MemberDetailDto> memberToMemberDetailPageDto(Page<Member> pageMembers){
        Page<MemberDetailDto> pageMemberDetailDtos = pageMembers.map(m-> new MemberDetailDto(m.id, m.name, m.subscribeCount));
        return pageMemberDetailDtos;
    }
    public static User memberToUser(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.authority.toString());
        return new User(
                String.valueOf(member.id),
                member.password,
                Collections.singleton(grantedAuthority)
        );
    }
}