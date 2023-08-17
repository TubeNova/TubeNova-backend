package TubeNova.app.service;

import TubeNova.app.domain.Member;
import TubeNova.app.domain.Subscribe;
import TubeNova.app.dto.SubscribeDto;
import TubeNova.app.repository.MemberRepository;
import TubeNova.app.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    @Transactional
    public SubscribeDto subscribeMember(Long id){
        Member me = memberService.getCurrentMember().get();
        Optional<Subscribe> optionalSubscribe = subscribeRepository.findByMemberAndTargetId(me, id);
        if(!optionalSubscribe.isPresent()){
            Subscribe subscribe = new Subscribe(me, id);
            SubscribeDto subscribeDto = subscribeRepository.save(subscribe).toSubscribeDto();
            memberRepository.findById(id).get().subscribeCountIncrease();
            return subscribeDto;
        }
        else{
            Subscribe subscribe = optionalSubscribe.get();
            subscribeRepository.delete(subscribe);
            memberRepository.findById(id).get().subscribeCountDecrease();
        }
        return null;
    }
}
