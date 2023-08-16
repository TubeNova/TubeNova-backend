package TubeNova.app.controller;

import TubeNova.app.dto.member.MemberCreateResponseDto;
import TubeNova.app.dto.member.MemberDetailDto;
import TubeNova.app.dto.member.MemberUpdateRequestDto;
import TubeNova.app.dto.member.MemberUpdateResponseDto;
import TubeNova.app.service.MemberService;
import TubeNova.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")  // 자신 객체 반환
    public ResponseEntity<MemberCreateResponseDto> findMemberById() {
        return ResponseEntity.ok(memberService.findMemberById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberCreateResponseDto> findMemberByUsername(@PathVariable String username) {
        return ResponseEntity.ok(memberService.findMemberByUsername(username));
    }
    @PutMapping("/me/update")
    public ResponseEntity<MemberUpdateResponseDto> updateMember(@RequestBody MemberUpdateRequestDto updateRequestDto){
        return ResponseEntity.ok(memberService.updateMember(updateRequestDto));
    }
    @GetMapping("/{name}")
    public ResponseEntity<MemberCreateResponseDto> findMemberByName(@PathVariable String name){
        return ResponseEntity.ok(memberService.findMemberByName(name));
    }

    @GetMapping("search/{keyword}")
    public Page<MemberDetailDto> searchMemberByKeyword(@PathVariable String keyword, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return memberService.searchMemberByKeyword(keyword,pageable);
    }
}