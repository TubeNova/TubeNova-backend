package TubeNova.app.dto.review;


import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewHeaderDto {
    private Long id;
    private String title;
    private String writer;

    private Long member_id;     //작성자 id
    private String linkURL;
    private int rating;
    private Category category;

    private LocalDateTime reviewCreatedTime;
    private int likes;
    private String videoImageUrl;       //영상 썸네일
    private String videoTitle;          //영상 제목
    private String channel;             //채널명
    private String contents;

    public static ReviewHeaderDto toReviewHeaderDto(Review review){
        ReviewHeaderDto reviewHeaderDto=new ReviewHeaderDto();
        reviewHeaderDto.setId(review.getId());
        reviewHeaderDto.setTitle(review.getTitle());
        reviewHeaderDto.setWriter(review.getWriter());
        reviewHeaderDto.setMember_id(reviewHeaderDto.getMember_id());   //작성자 id
        reviewHeaderDto.setLinkURL(review.getLinkURL());
        reviewHeaderDto.setRating(review.getRating());
        reviewHeaderDto.setReviewCreatedTime(review.getCreatedTime());
        reviewHeaderDto.setLikes(review.getLikes());
        reviewHeaderDto.setVideoImageUrl(review.getVideoImageUrl());
        reviewHeaderDto.setVideoTitle(review.getVideoTitle());
        reviewHeaderDto.setChannel(review.getChannel());
        reviewHeaderDto.setContents(review.getContents());
        return reviewHeaderDto;
    }
    @Builder
    public ReviewHeaderDto( Long id,String title, String writer, Long member_id, Category category, String linkURL, int rating, LocalDateTime reviewCreatedTime, int likes, String videoImageUrl, String videoTitle, String channel, String contents){
        this.videoImageUrl = videoImageUrl;
        this.videoTitle = videoTitle;
        this.channel = channel;
        this.id = id;
        this.title=title;
        this.member_id=member_id;
        this.writer=writer;
        this.category = category;
        this.linkURL=linkURL;
        this.rating=rating;
        this.reviewCreatedTime=reviewCreatedTime;
        this.likes=likes;
        this.contents = contents;
    }
}
