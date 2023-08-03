package TubeNova.app.dto.review;

import TubeNova.app.domain.Review;
import lombok.Getter;

@Getter
public class ReviewUpdateRequestDto {

    private String title;
    private String linkURL;
    private String contents;
    private String channelName;


    public static Review toReview(ReviewUpdateRequestDto dto){
        Review review = new Review().builder()
                .title(dto.getTitle())
                .linkURL(dto.getLinkURL())
                .contents(dto.getContents())
                .channelName(dto.getChannelName())
                .build();
        return review;
    }

}
