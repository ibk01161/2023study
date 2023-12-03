package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach                  // 메소드 끝날때 마다 실행되게 하는 어노테이션 (콜백 메소드)
    public void afterEach() {   // 저장소를 지워주는 메소드
        repository.clearStore();
    }

    // commit test

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        // 저장 후 id 세팅 (시퀀스 값 1 더해줌)
        repository.save(member);

        Member result = repository.findById(member.getId()).get();      // Optional에서 값을 꺼낼때는 get()을 이용해서 꺼낼 수 있음 (좋은 방법은 아님)
        // 검증
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);       // member가 result랑 같은지?
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();      // shift + F6 => 변수 rename 단축키
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
