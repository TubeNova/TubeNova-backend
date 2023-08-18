package TubeNova.app.repository;

import TubeNova.app.domain.Member;
import TubeNova.app.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Optional<Subscribe> findByMemberAndTargetId(Member me, Long id);
    List<Subscribe> findByMember(Member member);
}
