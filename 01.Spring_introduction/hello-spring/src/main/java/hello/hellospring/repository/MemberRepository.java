package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);         // Optional - findById의 값이 null일 수 있는데 Optional로 감싸서 null을 반환

    Optional<Member> findByName(String name);

    List<Member> findAll();

}
