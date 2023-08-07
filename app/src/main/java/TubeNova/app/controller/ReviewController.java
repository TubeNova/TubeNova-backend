package TubeNova.app.controller;


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

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @PostMapping()
    public String createReview(@RequestBody ReviewCreateRequestDto requestDto){
        return reviewService.createReview(requestDto);
    }
    @GetMapping("/latest")
    public String paging(@PageableDefault(page=1)
                         Pageable pageable, Model model){
        Page<ReviewHeaderDto> reviewList=reviewService.paging(pageable);
        int blockLimit=10;
        int startPage=(((int)(Math.ceil((double) pageable.getPageNumber()/blockLimit)))-1)
                * blockLimit+1;
        int endPage=((startPage+blockLimit-1)<reviewList.getTotalPages())
                ? startPage + blockLimit -1 : reviewList.getTotalPages();

        model.addAttribute("reviewList",reviewList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        return reviewList.toString();

    }

    @GetMapping("/popularity")
    public List<ReviewHeaderDto> giveList(){
        List<ReviewHeaderDto> reviewHeaderDtoList=reviewService.getList();

        return reviewHeaderDtoList;
    }



}
