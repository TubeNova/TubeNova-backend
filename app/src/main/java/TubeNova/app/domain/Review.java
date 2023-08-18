package TubeNova.app.domain;

import TubeNova.app.dto.review.ReviewHeaderDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
import TubeNova.app.dto.review.ReviewCreateResponseDto;
import TubeNova.app.dto.review.ReviewDetailDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.domain.Page;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
//@DynamicInsert
//@DynamicUpdate
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Column(nullable = false)   //영상 썸네일
    @JsonProperty
    private String videoImageUrl;

    @Column(nullable = false)   //영상 제목
    @JsonProperty
    private String videoTitle;

    @Column(nullable = false)   //채널명
    @JsonProperty
    private String channel;

    @Column(nullable = false)   //유튜브 영상 게시일
    @JsonProperty
    private String videoDate;

    @Column   //리뷰 제목
    private String title;

    @Column(nullable = false)   //작성자명
    @JsonProperty
    private String writer;

    @Column(nullable = false)   //영상링크
    @JsonProperty
    private String linkURL;

    @Column(nullable = false)   //내용
    @JsonProperty
    private String contents;

    @Column                     //별점
    @JsonProperty
    private int rating = 0;

    @Column                     //카테고리
    @JsonProperty
    private Category category;
    @JsonProperty
    private Integer likes = 0;  //좋아요 수

    @JsonIgnore                             //@ManyToOne의 Fetch 타입을 Lazy로 사용했을 때 나타나는 문제점, 비어있는 객체를 Serialize 하려다 에러가 발생
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeEntity> like = new ArrayList<>();


    @Builder
    public Review(String videoImageUrl, String videoTitle, String channel, String videoDate, String title, String linkURL, String writer, String contents, int rating, Category category, Member member, int likes){
        this.videoImageUrl = videoImageUrl;
        this.videoTitle = videoTitle;
        this.channel = channel;
        this.videoDate = videoDate;
        this.title = title;
        this.linkURL = linkURL;
        this.writer = writer;
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

    public ReviewDetailDto toReviewDto(){
        ReviewDetailDto dto = new ReviewDetailDto().builder()

                .videoImageUrl(videoImageUrl)
                .videoTitle(videoTitle)
                .channel(channel)
                .videoDate(videoDate)
                .title(title)
                .linkURL(linkURL)
                .contents(contents)
                .rating(rating)
                .category(category)
                .reviewCreatedTime(getCreatedTime())
                .likes(likes)
                .writer(writer)
                .member_id(member.getId())
                .build();

        return dto;
    }
    public static Page<ReviewHeaderDto> pageToHeaderDto(Page<Review> pageReviews){
        Page<ReviewHeaderDto> pageHeaderDtos = pageReviews.map(r-> ReviewHeaderDto.builder()
                .videoImageUrl(r.videoImageUrl)
                .videoTitle(r.videoTitle)
                .channel(r.channel)
                .title(r.title)
                .writer(r.writer)
                .linkURL(r.linkURL)
                .rating(r.rating)
                .reviewCreatedTime(r.getCreatedTime())
                .likes(r.likes)
                .category(r.category)
                .id(r.id)
                .member_id(r.member.getId())
                .build());
        return pageHeaderDtos;
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