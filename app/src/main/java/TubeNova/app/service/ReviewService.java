package TubeNova.app.service;

import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    public String createReview(ReviewCreateRequestDto requestDto){
        return "";
    }
}
