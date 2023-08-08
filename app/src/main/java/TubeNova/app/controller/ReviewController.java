package TubeNova.app.controller;

import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.dto.review.ReviewDetailDto;
import TubeNova.app.dto.review.ReviewUpdateRequestDto;
import TubeNova.app.service.LikeService;
import TubeNova.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reviews")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ReviewController {

    private final ReviewService reviewService;
    private final LikeService likeService;

    //리뷰 생성
    @PostMapping("/posts")
    public ResponseEntity<Object> createReview(@RequestBody ReviewCreateRequestDto requestDto){
        Review review = reviewService.createReview(requestDto);
        return (review != null) ?
                ResponseEntity.status(HttpStatus.OK).body(review):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //리뷰 상세
    @GetMapping("/{id}")
    public ResponseEntity<Object> detailReview(@PathVariable Long id){
        ReviewDetailDto reviewDetail = reviewService.getReviewDetail(id);
        return (reviewDetail != null) ?
                ResponseEntity.status(HttpStatus.OK).body(reviewDetail):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //리뷰 수정
    @PatchMapping("/updates/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto){
        Review review = reviewService.updateReview(id, requestDto);
        return (review != null) ?
                ResponseEntity.status(HttpStatus.OK).body(review):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //리뷰 삭제
    @DeleteMapping("/deletes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Review deleted = reviewService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}

