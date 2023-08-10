package TubeNova.app.repository;

import TubeNova.app.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAll(Pageable pageable);
    @Query(value = "SELECT * FROM Review WHERE created_time between date_add(now(), INTERVAL-1 WEEK) AND NOW() ORDER BY likes DESC LIMIT 10", nativeQuery = true)
    //List<Review> findTop10By(Sort likes);
    List<Review> findAll();

    @Query(value = "SELECT * FROM Review WHERE created_time between date_add(now(), INTERVAL-1 DAY) AND NOW() ORDER BY likes DESC LIMIT 10", nativeQuery = true)
        //List<Review> findTop10By(Sort likes);
    List<Review> findTop10By();
}
