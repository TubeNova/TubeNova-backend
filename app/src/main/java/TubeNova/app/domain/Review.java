package TubeNova.app.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
import TubeNova.app.dto.review.ReviewCreateResponseDto;
import TubeNova.app.dto.review.ReviewDetailDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
@DynamicInsert
@DynamicUpdate
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)   //리뷰 제목
    private String title;
    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)   //영상링크
    private String linkURL;

    @Column(nullable = false)   //내용
    private String contents;

    @Column                     //별점
    private int rating = 0;

    @Column                     //카테고리
    private Category category;

    private Integer likes = 0;  //좋아요 수

    @JsonIgnore                             //@ManyToOne의 Fetch 타입을 Lazy로 사용했을 때 나타나는 문제점, 비어있는 객체를 Serialize 하려다 에러가 발생
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeEntity> like = new ArrayList<>();


    @Builder
    public Review(String title, String linkURL, String contents, int rating, Category category, Member member, int likes){
        this.title = title;
        this.linkURL = linkURL;
        this.contents = contents;
        this.rating = rating;
        this.category = category;
        this.member = member;
        this.likes = likes;
    }

    public void addLike(){
        this.likes++;
    }

    public void subLikes(){
        this.likes--;
    }

    public ReviewDetailDto toReviewDto(Review review, String writer){
        ReviewDetailDto dto = new ReviewDetailDto().builder()
                .title(review.getTitle())
                .linkURL(review.getLinkURL())
                .contents(review.getContents())
                .rating(review.getRating())
                .category(review.getCategory())
                .reviewCreatedTime(review.getCreatedTime())
                .likes(review.getLikes())
                .writer(writer)
                .build();

        return dto;
    }

    //수정하기
    public void patch(Review review) {
        if(review.title != null)
            this.title = review.title;
        if(review.contents != null)
            this.contents = review.contents;
        if(review.category != null)
            this.category = review.category;
        this.rating = review.rating;
        this.likes = review.likes;

    }
}
