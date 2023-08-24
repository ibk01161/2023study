package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void 회원가입() {
        // given : 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        // when : 이거를 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과가 이게 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        // 이름을 검증
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
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
        try {
            memberService.join(member2);
            //Assertions.fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}