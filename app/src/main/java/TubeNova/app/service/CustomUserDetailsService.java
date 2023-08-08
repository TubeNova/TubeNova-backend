package TubeNova.app.service;

import TubeNova.app.domain.Member;
import TubeNova.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        if(optionalMember.isPresent()){
            return optionalMember.map(this::createUserDetails)
                    .orElseThrow(()->new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
        }
        System.out.println("return null");
            return null;
    }



    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        return Member.memberToUser(member);
    }

}