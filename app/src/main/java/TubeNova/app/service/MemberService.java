package TubeNova.app.service;

import TubeNova.app.domain.Category;
import TubeNova.app.domain.Member;
import TubeNova.app.domain.Subscribe;
import TubeNova.app.dto.member.MemberCreateResponseDto;
import TubeNova.app.dto.member.MemberDetailDto;
import TubeNova.app.dto.member.MemberUpdateRequestDto;
import TubeNova.app.dto.member.MemberUpdateResponseDto;
import TubeNova.app.repository.MemberRepository;
import TubeNova.app.repository.SubscribeRepository;
import TubeNova.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final AuthService authService;
    private final MemberRepository memberRepository;
    private final SubscribeRepository subscribeRepository;

    public Member findMemberById(Long memberId) {    //find Entity by id
        return memberRepository.findById(memberId).orElseThrow(()-> new UsernameNotFoundException("로그인 정보가 없습니다."));
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
            List<Category> updatedCategories = updateRequestDto.getCategories();
            member.setFavoriteCategory(updatedCategories);
            member = memberRepository.save(member);

            System.out.println("updated Password : " + member.getPassword());

            return Member.memberToUpdateResponseDto(member);
        }
        return null;
    }
    @Transactional
    public MemberDetailDto findMemberByName(String name){
        Member member =
        memberRepository.findByName(name).get();
        return member.memberToDetailDto();
    }
    //Page<MemberDetailDto>
    //return Member.memberToMemberDetailPageDto(pageMembers);
    @Transactional
    public Page<MemberDetailDto> searchMemberByKeyword(String keyword, Pageable pageable){
        Page<Member> pageMembers = memberRepository.findByNameContaining(keyword, pageable);
        Page<MemberDetailDto> pageMemberDto = Member.memberToMemberDetailPageDto(pageMembers);
        return pageMemberDto;
    }
    @Transactional
    public Page<MemberDetailDto> findMembersOrderBySubscribeCount(Pageable pageable){
        Page<Member> memberPage = memberRepository.findAllByOrderBySubscribeCountDesc(pageable);
        return Member.memberToMemberDetailPageDto(memberPage);
    }
    public Page<MemberDetailDto> findSubscribedMembers(Pageable pageable){
        Member me = getCurrentMember().get();
        List<Subscribe> subscribes = subscribeRepository.findByMember(me);
        List<Long> subscribedMemberIds = subscribes.stream().map(s -> s.getTargetId()).collect(Collectors.toList());
        Page<Member> subscribedMembers = memberRepository.findByIdInList(subscribedMemberIds, pageable);
        return Member.memberToMemberDetailPageDto(subscribedMembers);
    }
    public Optional<Member> getCurrentMember(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId());
    }
}