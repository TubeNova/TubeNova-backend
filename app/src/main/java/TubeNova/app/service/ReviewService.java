package TubeNova.app.service;

import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.dto.review.ReviewUpdateRequestDto;
import TubeNova.app.repository.MemberRepository;
import TubeNova.app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

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

        //2. 대상 엔티티 찾기
        Review target = reviewRepository.findById(id).orElse(null);

        //잘못된 요청 처리(대상이 없거나 id가 다른 경우)
        if (target == null || id != review.getId()) {
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
}
