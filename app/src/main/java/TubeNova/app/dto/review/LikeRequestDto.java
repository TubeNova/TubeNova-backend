package TubeNova.app.dto.review;

import TubeNova.app.domain.LikeEntity;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeRequestDto {
    private Long memberId;
    private Long reviewId;

    public LikeRequestDto(Long memberId, Long reviewId) {
        this.memberId = memberId;
        this.reviewId = reviewId;
    }

}