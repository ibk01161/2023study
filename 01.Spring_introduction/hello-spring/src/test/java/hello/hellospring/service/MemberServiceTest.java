package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    //MemberService memberService = new MemberService();
    // MemberService에 선언되어 있는 MemoryMemberRepository와 서로 다른 인스턴스 (다른 Repository를 이용하고 있는 것)
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 2023.08.26, 3-5 MemberServiceTest와 같은 Repository를 쓰도록 수정
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given : 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        // when : 이거를 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과가 이게 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        // 이름을 검증
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // 방법 1.
        /*
        try {
            memberService.join(member2);
            //Assertions.fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // 방법 2.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}