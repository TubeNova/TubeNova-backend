package TubeNova.app.controller;


import TubeNova.app.domain.Review;
import TubeNova.app.dto.review.ReviewCreateRequestDto;
import TubeNova.app.dto.review.ReviewHeaderDto;
import TubeNova.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Sort;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/create")
    public String createReview(@RequestBody ReviewCreateRequestDto requestDto){
        return reviewService.createReview(requestDto);
    }
    @GetMapping("/member/{member_id}")
    public Page getMemberReviewList(@PathVariable("member_id") Long member_id,
                               @PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC)
                               Pageable pageable, Model model){
        Page<ReviewHeaderDto> reviewList=reviewService.getMemberReviewList(member_id, pageable);
        int blockLimit=10;
        int startPage=(((int)(Math.ceil((double) pageable.getPageNumber()/blockLimit)))-1)
                * blockLimit+1;
        int endPage=((startPage+blockLimit-1)<reviewList.getTotalPages())
                ? startPage + blockLimit -1 : reviewList.getTotalPages();

        model.addAttribute("reviewList",reviewList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        return reviewList;

    }

    @GetMapping("/latest")
    public Page getLatsetList(@PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC)
                         Pageable pageable, Model model){
        Page<ReviewHeaderDto> reviewList=reviewService.getLatestList(pageable);
        int blockLimit=10;
        int startPage=(((int)(Math.ceil((double) pageable.getPageNumber()/blockLimit)))-1)
                * blockLimit+1;
        int endPage=((startPage+blockLimit-1)<reviewList.getTotalPages())
                ? startPage + blockLimit -1 : reviewList.getTotalPages();

        model.addAttribute("reviewList",reviewList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        return reviewList;

    }

    @GetMapping("/latest/{category}")
    public Page getLatestCategoryList(@PathVariable("category") String category,
                                             @PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<ReviewHeaderDto> reviewList=reviewService.getLatestCateList(category, pageable);
        int blockLimit=10;
        int startPage=(((int)(Math.ceil((double) pageable.getPageNumber()/blockLimit)))-1)
                * blockLimit+1;
        int endPage=((startPage+blockLimit-1)<reviewList.getTotalPages())
                ? startPage + blockLimit -1 : reviewList.getTotalPages();

        model.addAttribute("reviewList",reviewList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        return reviewList;
    }

    @GetMapping("/weekly-popularity")
    public List<ReviewHeaderDto> getWeekList(){
        List<ReviewHeaderDto> reviewHeaderDtoList=reviewService.getWeekList();

        return reviewHeaderDtoList;
    }

    @GetMapping("/weekly-popularity/{category}")
    public List<ReviewHeaderDto> getWeekCategoryList(@PathVariable("category") String category){
        List<ReviewHeaderDto> reviewHeaderDtoList=reviewService.getWeekCategoryList(category);

        return reviewHeaderDtoList;
    }

    @GetMapping("/favorite-categories")
    public Page<Review> getFavoriteReviews(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return reviewService.getFavoriteReviews(pageable);
    }
    @GetMapping("/{category}")
    public Page<Review> findReviewByCategory(@PathVariable("category") String category,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return reviewService.findReviewByCategory(category, pageable);
    }
}
