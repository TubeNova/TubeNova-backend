package TubeNova.app.controller;

import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @PostMapping()
    public String createReview(@RequestBody ReviewCreateRequestDto requestDto){
        return reviewService.createReview(requestDto);
    }
}
