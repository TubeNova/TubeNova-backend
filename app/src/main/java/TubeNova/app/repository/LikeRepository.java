package TubeNova.app.repository;

import TubeNova.app.domain.LikeEntity;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Optional<Object> findByReview_IdAndMember_Id(Long reviewId, Long memberId);

}