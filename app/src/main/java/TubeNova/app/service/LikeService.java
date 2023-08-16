package TubeNova.app.service;

import TubeNova.app.domain.LikeEntity;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.LikeRequestDto;
import TubeNova.app.repository.LikeRepository;
import TubeNova.app.repository.MemberRepository;
import TubeNova.app.repository.ReviewRepository;
import jakarta.transaction.Transactional;
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


    @Transactional
    public LikeEntity createLike(LikeRequestDto likeRequestDto) {

        //1. 받은 member_id와 review_id 조회
        Member member = memberRepository.findById(likeRequestDto.getMemberId()).orElse(null);
        Review review = reviewRepository.findById(likeRequestDto.getReviewId()).orElse(null);

        if (member == null || review == null) {
            return null;
        }

        // 이미 좋아요되어있으면 에러 반환
        if (likeRepository.findByReview_IdAndMember_Id(member.getId(),review.getId()).isPresent()){
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

        LikeEntity like = (LikeEntity) likeRepository.findByReview_IdAndMember_Id(member.getId(), review.getId()).orElse(null);

        if (like == null) return null;

        likeRepository.delete(like);
        review.subLikes();
        reviewRepository.save(review);
        return like;
    }
}
