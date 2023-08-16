package TubeNova.app.dto.review;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class ReviewDetailDto {
    private String title;
    private String linkURL;
    private int rating;
    private LocalDateTime reviewCreatedTime;
    private int likes;
    private String contents;
    private String writer;
    private Category category;


    @Builder
    public ReviewDetailDto(String title, String linkURL, int rating, LocalDateTime reviewCreatedTime, int likes, String contents, String writer, Category category) {
        this.title = title;
        this.linkURL = linkURL;
        this.rating = rating;
        this.reviewCreatedTime = reviewCreatedTime;
        this.likes = likes;
        this.contents = contents;
        this.writer = writer;
        this.category = category;
    }

}
