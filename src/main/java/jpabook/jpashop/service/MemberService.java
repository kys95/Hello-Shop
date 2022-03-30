package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
     public Long join(Member member){

         validateDuplicateMember(member);   //중복 회원 검증
         memberRepository.save(member);
         return member.getId();
     }

    private void validateDuplicateMember(Member member) {   //멀티스레드 고려하여 member의 name을 unique 제약조건으로
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
         return memberRepository.findAll();
    }

    //회원 한건 조회
    public Member findOne(Long memberId){
         return memberRepository.findOne(memberId);
    }


    // update할 때, return값은 없는게 나음. 있더라도 id값 정도만
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);   //스프링이 aop가 동작하면서 트랜잭션과 관련된 aop가 끝나는 시점에 커밋이 되어 그때 Jpa가 flush하고
                                // 영속성 컨텍스트 커밋함 -> 변경 감지
    }
}

































