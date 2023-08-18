package TubeNova.app.dto.review;

import TubeNova.app.domain.Category;
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
    private String contents;
    private String writer;
    private String linkURL;
    private int rating;
    private Category category;
    private LocalDateTime reviewCreatedTime;
    private int likes;

    public static ReviewHeaderDto toReviewHeaderDto(Review review){
        ReviewHeaderDto reviewHeaderDto=new ReviewHeaderDto();
        reviewHeaderDto.setId(review.getId());
        reviewHeaderDto.setTitle(review.getTitle());
        reviewHeaderDto.setContents(review.getContents());
        reviewHeaderDto.setWriter(review.getWriter());
        reviewHeaderDto.setLinkURL(review.getLinkURL());
        reviewHeaderDto.setRating(review.getRating());
        reviewHeaderDto.setReviewCreatedTime(review.getCreatedTime());
        reviewHeaderDto.setLikes(review.getLikes());

        return reviewHeaderDto;
    }
    @Builder
    public ReviewHeaderDto(Long id,String title, String contents, String writer, Category category, String linkURL, int rating, LocalDateTime reviewCreatedTime, int likes){
        this.id = id;
        this.title=title;
        this.contents=contents;
        this.writer=writer;
        this.category = category;
        this.linkURL=linkURL;
        this.rating=rating;
        this.reviewCreatedTime=reviewCreatedTime;
        this.likes=likes;
    }
}