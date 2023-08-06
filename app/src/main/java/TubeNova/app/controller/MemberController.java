package TubeNova.app.controller;


import TubeNova.app.domain.Member;
import TubeNova.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository ms;

    @PostMapping("/member")
    public void create(){
        Member member = new Member("azs@az", "password","dltmdwns");


        ms.save(member);
    }

    @GetMapping("/member")
    public List<Member> getMember(){
        return ms.findAll();
    }

}
