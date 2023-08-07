package TubeNova.app.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String linkURL;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String channelName;
    @Column
    private int rating = 0;
    private Integer likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Builder
    public Review(String title, String linkURL, String contents, Member member, String channelName, int likes){
        this.title = title;
        this.linkURL = linkURL;
        this.contents = contents;
        this.member = member;
        this.channelName = channelName;
        this.likes = likes;
    }

    public void addLike(){
        this.likes++;
    }

    public void subLikes(){
        this.likes--;
    }

}
