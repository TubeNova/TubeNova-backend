package TubeNova.app.dto.review;

import TubeNova.app.domain.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewHeaderDto {
    private String title;
    private String writer;
    private String linkURL;
    private int rating;
    private LocalDateTime reviewCreatedTime;
    private int likes;

    public static ReviewHeaderDto toReviewHeaderDto(Review review){
        ReviewHeaderDto reviewHeaderDto=new ReviewHeaderDto();
        reviewHeaderDto.setTitle(review.getTitle());
        reviewHeaderDto.setWriter(review.getWriter());
        reviewHeaderDto.setLinkURL(review.getLinkURL());
        reviewHeaderDto.setRating(review.getRating());
        reviewHeaderDto.setReviewCreatedTime(review.getCreatedTime());
        reviewHeaderDto.setLikes(review.getLikes());

        return reviewHeaderDto;
    }

    public ReviewHeaderDto(String title, String writer, String linkURL, int rating, LocalDateTime reviewCreatedTime, int likes){
        this.title=title;
        this.writer=writer;
        this.linkURL=linkURL;
        this.rating=rating;
        this.reviewCreatedTime=reviewCreatedTime;
        this.likes=likes;
    }
}