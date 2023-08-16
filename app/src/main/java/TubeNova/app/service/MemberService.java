package TubeNova.app.service;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.dto.member.MemberCreateResponseDto;
import TubeNova.app.dto.member.MemberDetailDto;
import TubeNova.app.dto.member.MemberUpdateRequestDto;
import TubeNova.app.dto.member.MemberUpdateResponseDto;
import TubeNova.app.repository.MemberRepository;
import TubeNova.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final AuthService authService;
    private final MemberRepository memberRepository;

    public MemberCreateResponseDto findMemberById(Long memberId) {    //find Entity by id
        return memberRepository.findById(memberId)
                .map(Member::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public MemberCreateResponseDto findMemberByUsername(String username) {   //find Entity by username
        return memberRepository.findByEmail(username)
                .map(Member::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }
    @Transactional
    public MemberUpdateResponseDto updateMember(MemberUpdateRequestDto updateRequestDto) {
        Optional<Member> optionalMember = getCurrentMember();
        String originalPassword = updateRequestDto.getOriginalPassword();
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setName(updateRequestDto.getName());

            System.out.println("original Password : " + member.getPassword());

            member = authService.changePassword(member, originalPassword, updateRequestDto.getUpdatedPassword());
            List<Category> updatedCategories = Category.toCategories(updateRequestDto.getCategories());
            member.setFavoriteCategory(updatedCategories);
            member = memberRepository.save(member);

            System.out.println("updated Password : " + member.getPassword());

            return Member.memberToUpdateResponseDto(member);
        }
        return null;
    }
    public MemberCreateResponseDto findMemberByName(String name){
        return Member.of(memberRepository.findByName(name).get());
    }
    public Page<MemberDetailDto> searchMemberByKeyword(String keyword, Pageable pageable){
        Page<Member> pageMembers = memberRepository.findByNameContaining(keyword, pageable);
        return Member.memberToMemberDetailPageDto(pageMembers);
    }
    public Optional<Member> getCurrentMember(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId());
    }
}