package TubeNova.app.dto.review;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Review;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ReviewCreateRequestDto {
    private String videoImageUrl;       //영상 썸네일
    private String videoTitle;          //영상 제목
    private String channel;             //채널명
    private String videoDate;           //유튜브 영상 게시일
    private String title = null;        //리뷰 제목
    private String linkURL;             //영상 링크
    private String contents;            //리뷰 내용
    private String writer;              //작성자
    private int rating;                 //별점
    private Category category;          //카테고리
    private LocalDateTime createdTime;  //리뷰 생성 날짜
    private Long id;                    //memberId

    public static Review toReview(ReviewCreateRequestDto dto, Member member){

        Review review = new Review().builder()
                .videoImageUrl(dto.getVideoImageUrl())
                .videoTitle(dto.getVideoTitle())
                .channel(dto.getChannel())
                .videoDate(dto.getVideoDate())
                .title(dto.getTitle())
                .linkURL(dto.getLinkURL())
                .writer(member.getName())
                .contents(dto.getContents())
                .rating(dto.getRating())
                .category(dto.getCategory())
                .member(member)
                .build();
        return review;
    }
}