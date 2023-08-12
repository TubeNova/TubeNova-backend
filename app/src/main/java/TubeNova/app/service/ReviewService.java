package TubeNova.app.service;


import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.dto.review.ReviewHeaderDto;
import TubeNova.app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    public String createReview(ReviewCreateRequestDto requestDto){
        Review review = ReviewCreateRequestDto.toReview(requestDto);
        reviewRepository.save(review);
        return review.toString();
    }

    public Page<ReviewHeaderDto> getMemberReviewList (Long memberId, Pageable pageable){
//        int page = pageable.getPageNumber()-1;
//        int pageLimit=10;
//        Page<Review> reviews=
//                reviewRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));

        Page<Review> reviews=
                reviewRepository.findReviewByMemberId(memberId, pageable);
        Page<ReviewHeaderDto> reviewHeaderDtos=reviews.map(review ->
                new ReviewHeaderDto(review.getTitle()
                        ,review.getWriter()
                        ,review.getLinkURL()
                        ,review.getRating()
                        ,review.getCreatedTime()
                        ,review.getLikes()));
        return reviewHeaderDtos;
    }
    public Page<Review> getFavoriteReviews(Pageable pageable){
        Member currentMember = memberService.getCurrentMember().get();
        List<Category> categories = currentMember.getCategories();
        return reviewRepository.findByCategories(categories, pageable);
    }
    public Page<Review> findReviewByCategory(String category, Pageable pageable){
        return reviewRepository.findByCategory(Category.toCategory(category), pageable);
    }


    public Page<ReviewHeaderDto> getLatestList (Pageable pageable){
//        int page = pageable.getPageNumber()-1;
//        int pageLimit=10;
//        Page<Review> reviews=
//                reviewRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));

        Page<Review> reviews=
                reviewRepository.findAll(pageable);
        Page<ReviewHeaderDto> reviewHeaderDtos=reviews.map(review ->
                new ReviewHeaderDto(review.getTitle()
                        ,review.getWriter()
                        ,review.getLinkURL()
                        ,review.getRating()
                        ,review.getCreatedTime()
                        ,review.getLikes()));
        return reviewHeaderDtos;
    }

    @Transactional
    public Page<ReviewHeaderDto> getLatestCateList(String category, Pageable pageable){
//        int page = pageable.getPageNumber()-1;
//        int pageLimit=10;
        Page<Review> reviews=
                reviewRepository.findByCategory(Category.toCategory(category), pageable);
        Page<ReviewHeaderDto> reviewHeaderDtos=reviews.map(review ->
                new ReviewHeaderDto(review.getTitle()
                        ,review.getWriter()
                        ,review.getLinkURL()
                        ,review.getRating()
                        ,review.getCreatedTime()
                        ,review.getLikes()));
        return reviewHeaderDtos;
    }

    @Transactional
    public List<ReviewHeaderDto> getWeekList(){
        LocalDateTime startDatetime = LocalDateTime.now().minusWeeks(1);
        LocalDateTime endDatetime = LocalDateTime.now();
        List<Review> reviewList=reviewRepository.findTop10ByCreatedTimeBetween(startDatetime, endDatetime, Sort.by(Sort.Direction.DESC, "likes"));
        List<ReviewHeaderDto> reviewHeaderDtoList=new ArrayList<>();
        for (Review review: reviewList){
            reviewHeaderDtoList.add(ReviewHeaderDto.toReviewHeaderDto(review));
        }
        return reviewHeaderDtoList;
    }
    @Transactional
    public List<ReviewHeaderDto> getWeekCategoryList(String category){
        LocalDateTime startDatetime = LocalDateTime.now().minusWeeks(1);
        LocalDateTime endDatetime = LocalDateTime.now();
        List<Review> reviewList=reviewRepository.findTop10ByCategoryAndCreatedTimeBetween(Category.toCategory(category), startDatetime, endDatetime, Sort.by(Sort.Direction.DESC, "likes"));
        List<ReviewHeaderDto> reviewHeaderDtoList=new ArrayList<>();
        for (Review review: reviewList){
            reviewHeaderDtoList.add(ReviewHeaderDto.toReviewHeaderDto(review));
        }
        return reviewHeaderDtoList;
    }

}
