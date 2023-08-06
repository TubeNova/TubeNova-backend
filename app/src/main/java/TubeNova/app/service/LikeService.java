package TubeNova.app.service;

import TubeNova.app.domain.LikeEntity;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.LikeRequestDto;
import TubeNova.app.repository.LikeRepository;
import TubeNova.app.repository.MemberRepository;
import TubeNova.app.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
//@RequiredArgsConstructor
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReviewRepository reviewRepository;


//    //내가 좋아요를 했는지 체크
//    public int findLike(Long reviewId, Long memberId) {
//        // 저장된 DTO 가 없다면 0, 있다면 1
//        Optional<Object> findLike = likeRepository.findByReviewEntity_IdAndMemberEntity_Id(reviewId, memberId);
//
//
//        if (findLike.isEmpty()){
//            return 0;
//        }else {
//
//            return 1;
//        }
//    }

    @Transactional
    public LikeEntity createLike(LikeRequestDto likeRequestDto) {

        //1. 받은 member_id와 review_id 조회
        Member member = memberRepository.findById(likeRequestDto.getMemberId()).orElse(null);
        Review review = reviewRepository.findById(likeRequestDto.getReviewId()).orElse(null);

        if (member == null || review == null) {
            return null;
        }

        // 이미 좋아요되어있으면 에러 반환
        if (likeRepository.findByMemberAndReview(member, review).isPresent()){
            //TODO 409에러로 변경
            return null;
        }

        LikeEntity like = LikeEntity.builder()
                .review(review)
                .member(member)
                .build();

        likeRepository.save(like);
//        reviewRepository.addLike(review);
        log.info("like 수 추가 전: "+review.getLikes());
        review.addLike();
        log.info("like 수 추가 후: "+review.getLikes());
        reviewRepository.save(review);

        return like;
    }

    @Transactional
    public LikeEntity delete(LikeRequestDto heartRequestDTO) {

        Member member = memberRepository.findById(heartRequestDTO.getMemberId()).orElse(null);
        Review review = reviewRepository.findById(heartRequestDTO.getReviewId()).orElse(null);

        if (member == null || review == null) return null;

        LikeEntity like = (LikeEntity) likeRepository.findByMemberAndReview(member, review).orElse(null);

        if (like == null) return null;

//        reviewRepository.subLike(review);
        review.subLikes();
        reviewRepository.save(review);
        return like;
    }
}
