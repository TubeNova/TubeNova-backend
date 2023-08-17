package TubeNova.app.controller;

import TubeNova.app.dto.SubscribeDto;
import TubeNova.app.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//@CrossOrigin
@Controller
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService subscribeService;
    @PostMapping("/subscribe/{memberId}")
    public ResponseEntity<SubscribeDto> subscribeMember(@PathVariable Long memberId){
        return ResponseEntity.ok(subscribeService.subscribeMember(memberId));
    }
}
