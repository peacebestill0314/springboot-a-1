package com.study.springboota1.repository;

import com.study.springboota1.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // todo static, Map추상 HashMap구현
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // todo null일 가능성이 있는 경우 optional로 감싸 리턴하면 프론트에서 어떠한 처리 가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // todo lambda stream
        // 참고 https://sehun-kim.github.io/sehun/java-lambda-stream/
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
