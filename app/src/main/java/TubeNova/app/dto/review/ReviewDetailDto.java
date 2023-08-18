package TubeNova.app.dto.review;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ReviewDetailDto {
    private String videoImageUrl;       //영상 썸네일
    private String videoTitle;          //영상 제목
    private String channel;             //채널명
    private String videoDate;           //유튜브 영상 게시일
    private String title;
    private String linkURL;
    private int rating;
    private LocalDateTime reviewCreatedTime;
    private int likes;
    private String contents;
    private String writer;
    private Category category;
    private Long id;
    private Long memberId;
    private boolean memberLike;

    @Builder
    public ReviewDetailDto(String videoImageUrl, String videoTitle, String channel, String videoDate, String title, String linkURL, int rating, LocalDateTime reviewCreatedTime, int likes, String contents, String writer, Category category, Long id, Long memberId) {
        this.videoImageUrl = videoImageUrl;
        this.videoTitle = videoTitle;
        this.channel = channel;
        this.videoDate = videoDate;
        this.title = title;
        this.linkURL = linkURL;
        this.rating = rating;
        this.reviewCreatedTime = reviewCreatedTime;
        this.likes = likes;
        this.contents = contents;
        this.writer = writer;
        this.category = category;
        this.id = id;
        this.memberId=memberId;
    }

}
