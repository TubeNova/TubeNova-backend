package TubeNova.app.repository;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.LikeEntity;
import TubeNova.app.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.category in :categories")
    Page<Review> findByCategories(@Param("categories")List<Category> categories, Pageable pageable);
    Page<Review> findByCategoryOrderById(Category category, Pageable pageable);
    Page<Review> findByCategoryOrderByLikes(Category category, Pageable pageable);
    Page<Review> findByCategory(Category category, Pageable pageable);
    List<Review> findTop10ByCreatedTimeBetween(LocalDateTime start, LocalDateTime end, Sort likes);
    Page<Review> findReviewByMemberId(Long memberId, Pageable pageable);
    List<Review> findTop10ByCategoryAndCreatedTimeBetween(Category category, LocalDateTime start, LocalDateTime end, Sort likes);

    Page<Review> findByTitleContaining(String keyword, Pageable pageable);  //findBy'Column'Containing 을 쓰면 컬럼내에 해당된 것들을 찾아준다.
    @Query("SELECT r FROM Review r where r.category in :categories")
    Page<Review> findByFavoriteCategories(List<Category> categories, Pageable pageable);

    @Query("SELECT r FROM Review r where r.like in :likeList")
    Page<Review> findByLikes(List<LikeEntity> likeList, Pageable pageable);
}