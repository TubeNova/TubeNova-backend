package TubeNova.app.dto.review;

import TubeNova.app.domain.Review;
import lombok.Getter;

@Getter
public class ReviewCreateRequestDto {
    private String title;
    private String linkURL;
    private String contents;
    private String channelName;

    public static Review toReview(String title, String linkURL, String contents, String channelName){
        Review review = new Review().builder()
                .title(title)
                .linkURL(linkURL)
                .contents(contents)
                .channelName(channelName)
                .build();
        return review;
    }
}
