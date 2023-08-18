package TubeNova.app.controller;

import TubeNova.app.domain.Member;
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
    public ResponseEntity<MemberCreateResponseDto> findMe() {
        return ResponseEntity.ok(Member.of(memberService.findMemberById(SecurityUtil.getCurrentMemberId())));
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberDetailDto> findMemberByUsername(@PathVariable String username) {
        return ResponseEntity.ok(memberService.findMemberByUsername(username));
    }
    @PutMapping("/me/update")
    public ResponseEntity<MemberUpdateResponseDto> updateMember(@RequestBody MemberUpdateRequestDto updateRequestDto){
        return ResponseEntity.ok(memberService.updateMember(updateRequestDto));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<MemberDetailDto> findMemberByName(@PathVariable String name){
        return ResponseEntity.ok(memberService.findMemberByName(name));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<MemberDetailDto> findMemberById(@PathVariable Long id){
        return ResponseEntity.ok(memberService.findMemberById(id).memberToDetailDto());
    }
    //
    @GetMapping("/search/{keyword}")
    public @ResponseBody Page<MemberDetailDto> searchMemberByKeyword(@PathVariable String keyword, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<MemberDetailDto> memberDetailDtos =  memberService.searchMemberByKeyword(keyword,pageable);
        return memberDetailDtos;
    }
    @GetMapping("/order-by-subcount")
    public @ResponseBody Page<MemberDetailDto> findMembersOrderBySubscribeCount(@PageableDefault(size = 20) Pageable pageable){
        Page<MemberDetailDto> memberDetailDtos =  memberService.findMembersOrderBySubscribeCount(pageable);
        return memberDetailDtos;
    }
    @GetMapping("/subscribed")
    public @ResponseBody Page<MemberDetailDto> findSubscribedMembers(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<MemberDetailDto> subscribedMembers = memberService.findSubscribedMembers(pageable);
        return subscribedMembers;
    }
}
