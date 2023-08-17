package TubeNova.app.controller;


import TubeNova.app.domain.LikeEntity;
import TubeNova.app.dto.review.LikeRequestDto;
import TubeNova.app.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody LikeRequestDto likeRequestDto) {
        LikeEntity like = likeService.createLike(likeRequestDto);
        return (like != null) ?
                ResponseEntity.status(HttpStatus.OK).body(like):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody LikeRequestDto likeRequestDto) {
        LikeEntity like = likeService.delete(likeRequestDto);
        return (like != null) ?
                ResponseEntity.status(HttpStatus.OK).body(like):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
