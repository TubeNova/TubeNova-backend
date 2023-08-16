package TubeNova.app.repository;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findTop10ByCreatedTimeBetween(LocalDateTime start, LocalDateTime end, Sort likes);
    Page<Review> findReviewByMemberId(Long memberId, Pageable pageable);
    List<Review> findTop10ByCategoryAndCreatedTimeBetween(Category category, LocalDateTime start, LocalDateTime end, Sort likes);

    @Query("select r from Review r where r.category in :categories")
    Page<Review> findByCategories(@Param("categories")List<Category> categories, Pageable pageable);
    Page<Review> findByCategory(Category category, Pageable pageable);
}