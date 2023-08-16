package TubeNova.app.service;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.*;
import TubeNova.app.repository.LikeRepository;
import TubeNova.app.repository.MemberRepository;
import TubeNova.app.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ReviewRepository reviewRepository;
    private final LikeRepository likeRepository;

    public Review createReview(ReviewCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getId()).orElse(null);
        if (member == null) {
            return null;
        }

        Review review = requestDto.toReview(requestDto, member);
        if(review.getId() != null){
            return null;
        }
        reviewRepository.save(review);
        return review;
    }

    public Review updateReview(Long id, ReviewUpdateRequestDto requestDto) {

        //1. 수정용 엔티티 생성
        Review review = requestDto.toReview(requestDto);
        log.info("review -> {}",review.getId());

        //2. 대상 엔티티 찾기
        Review target = reviewRepository.findById(id).orElse(null);
        log.info("target -> {}", target.getId());

        //잘못된 요청 처리(대상이 없거나 id가 다른 경우)
        if (target == null || id != target.getId()) {
            log.info("잘못된 요청! id: {}, review: {}", id, review.toString());
            return null;
        }

        //업데이트
        target.patch(review);
        Review updated = reviewRepository.save(target);
        return updated;

    }

    public Review delete(Long id) {
        //대상 찾기
        Review target = reviewRepository.findById(id).orElse(null);

        //잘못된 요청 처리
        if(target == null){
            return null;
        }

        //대상 삭제
        reviewRepository.delete(target);
        return target;
    }

    public Page<Review> getFavoriteReviews(Pageable pageable){
        Member currentMember = memberService.getCurrentMember().get();
        List<Category> categories = currentMember.getCategories();
        return reviewRepository.findByCategories(categories, pageable);
    }

    public Page<Review> findReviewByCategory(String category, Pageable pageable){
        return reviewRepository.findByCategory(Category.toCategory(category), pageable);
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




    public ReviewDetailDto getReviewDetail(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return null;
        }

        Member member = memberRepository.findById(review.getMember().getId()).orElse(null);
        if (member == null) {
            return null;
        }

        ReviewDetailDto rd = review.toReviewDto(review, member.getName());

        return rd;
    }

    public int findLike(Long id, Long memberId) {
        Optional<Object> findLike = likeRepository.findByReview_IdAndMember_Id(id, memberId);


        if (findLike.isEmpty()){
            return 0;
        }else {

            return 1;
        }
    }

    public Page<Review> searchReviews(String keyword, Pageable pageable) {
        return reviewRepository.findByTitleContaining(keyword, pageable);



    }
}
