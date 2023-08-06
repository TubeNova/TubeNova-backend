package TubeNova.app.controller;

import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/create")
    public String createReview(@RequestBody ReviewCreateRequestDto requestDto){
        return reviewService.createReview(requestDto);
    }
    @GetMapping("/favorite-categories")
    public Page<Review> getFavoriteReviews(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return reviewService.getFavoriteReviews(pageable);
    }
    @GetMapping("/{category}")
    public Page<Review> findReviewByCategory(@PathVariable("category") String category,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return reviewService.findReviewByCategory(category, pageable);
    }
}
