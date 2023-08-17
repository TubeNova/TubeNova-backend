package TubeNova.app.dto.review;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class ReviewCreateRequestDto {
    private String title;
    private String linkURL;
    private String contents;
    private int rating;
    private Category category;
    private LocalDateTime createdTime;  //따로 받지 않아도 될거 같음

    public static Review toReview(ReviewCreateRequestDto dto, Member member){

        Review review = new Review().builder()
                .title(dto.getTitle())
                .linkURL(dto.getLinkURL())
                .contents(dto.getContents())
                .rating(dto.getRating())
                .category(dto.getCategory())
                .member(member)
                .writer(member.getName())
                .build();
        return review;
    }
}

