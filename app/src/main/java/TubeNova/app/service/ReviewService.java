package TubeNova.app.service;

import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.dto.review.ReviewHeaderDto;
import TubeNova.app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    public String createReview(ReviewCreateRequestDto requestDto){
        return "";
    }

    @Transactional
    public List<ReviewHeaderDto> getList(){
        List<Review> reviewList=reviewRepository.findTop10By(Sort.by(Sort.Direction.DESC, "likes"));
        List<ReviewHeaderDto> reviewHeaderDtoList=new ArrayList<>();
        for (Review review: reviewList){
            reviewHeaderDtoList.add(ReviewHeaderDto.toReviewHeaderDto(review));
        }
        return reviewHeaderDtoList;
    }

    public Page<ReviewHeaderDto> paging (Pageable pageable){
        int page = pageable.getPageNumber()-1;
        int pageLimit=10;
        Page<Review> reviews=
                reviewRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
        Page<ReviewHeaderDto> reviewHeaderDtos=reviews.map(review ->
                new ReviewHeaderDto(review.getTitle()
                        ,review.getWriter()
                        ,review.getLinkURL()
                        ,review.getRating()
                        ,review.getCreatedTime()
                        ,review.getLikes()));
        return reviewHeaderDtos;
    }

}
