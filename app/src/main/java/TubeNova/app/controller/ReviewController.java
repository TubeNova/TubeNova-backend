package TubeNova.app.controller;

import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.dto.review.ReviewDetailDto;
import TubeNova.app.dto.review.ReviewUpdateRequestDto;
import TubeNova.app.service.ReviewService;
import TubeNova.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/reviews")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 생성
    @PostMapping("/posts")
    public ResponseEntity<Object> createReview(@RequestBody ReviewCreateRequestDto requestDto){
        Review review = reviewService.createReview(requestDto);
        return (review != null) ?
                ResponseEntity.status(HttpStatus.OK).body(review):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //리뷰 상세
    @GetMapping("/details/{id}")
    public ResponseEntity<Object> detailReview(@PathVariable Long id){
        ReviewDetailDto reviewDetail = reviewService.getReviewDetail(id);
        int like = reviewService.findLike(id, SecurityUtil.getCurrentMemberId());   //좋아요 했으면 1, 아니면 0
        reviewDetail.setMemberLike(like);
        return (reviewDetail != null) ?
                ResponseEntity.status(HttpStatus.OK).body(reviewDetail):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //리뷰 수정
    @PutMapping("/updates/{id}")
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

    @GetMapping("/favorite-categories")
    public Page<Review> getFavoriteReviews(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return reviewService.getFavoriteReviews(pageable);
    }

    @GetMapping("/{category}")
    public Page<Review> findReviewByCategory(@PathVariable("category") String category,
                                             @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return reviewService.findReviewByCategory(category, pageable);
    }

    //리뷰 검색
    @GetMapping("/search")
    public Page<Review> searchReview(@RequestParam(value = "keyword") String keyword,
                                                      @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Review> reviews = reviewService.searchReviews(keyword, pageable);
        return reviews;
    }

}

