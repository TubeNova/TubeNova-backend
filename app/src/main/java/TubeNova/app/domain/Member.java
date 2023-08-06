package TubeNova.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name= "Member")
public class Member {
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name ="favorite_category")
    private Category favoriteCategory;

    @Builder
    public Member(String email, String password, String name, Category favoriteCategory, Authority authority){
        this.email = email;
        this.password = password;
        this.name = name;
        this.favoriteCategory = favoriteCategory;
        this.authority = authority;
    }

    public Member(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeEntity> like = new ArrayList<>();
}
