package TubeNova.app.dto.review;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Review;
import lombok.Getter;

@Getter
public class ReviewCreateRequestDto {
    private String title;
    private String linkURL;
    private String contents;
    private String channelName;
    private String category;
    public static Review toReview(ReviewCreateRequestDto requestDto){
        Review review = new Review().builder()
                .title(requestDto.title)
                .linkURL(requestDto.linkURL)
                .contents(requestDto.contents)
                .channelName(requestDto.channelName)
                .category(Category.toCategory(requestDto.category))
                .build();
        return review;
    }
}
