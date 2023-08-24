package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository {

    // save 할때 저장할 Map
    private static Map<Long, Member> store = new HashMap<>();
    // 시퀀스 (0,1,2...)
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // 시퀀스 값을 1 올려줌
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과가 null 일때 optional로 감쌈
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
