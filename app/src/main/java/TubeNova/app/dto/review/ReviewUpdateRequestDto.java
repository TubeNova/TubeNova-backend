package TubeNova.app.dto.review;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewUpdateRequestDto {

    private String title;
    private String contents;
    private int rating;
    private Category category;
    private LocalDateTime udatedTime;  //따로 받지 않아도 될거 같음
    private Long id;


    public static Review toReview(ReviewUpdateRequestDto dto){
        Review review = new Review().builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .rating(dto.getRating())
                .category(dto.getCategory())
                .build();
        return review;
    }

}
