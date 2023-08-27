package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // 회원 repository
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 2023.08.26, 3-5 MemberServiceTest와 같은 Repository를 쓰도록 수정
    private final MemberRepository memberRepository;

    // MemberService에 memberRepository를 외부에서 넣어주도록 변경 
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같이 이름이 있는 중복 회원 X
        memberRepository.findByName(member.getName())        // Optional 안에 객체가 있음
                .ifPresent(m -> {                            // 객체의 값이 있으면 이 로직을 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 로직
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
