package com.study.springboota1.service;

import com.study.springboota1.domain.Member;
import com.study.springboota1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // TODO 2. 어디서나 같은 인스턴스 쓰게 바꾸기
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // TODO 3. 서비스 생성시에 외부에서 넣어주도록 -> DI 생성자 주입
    //@Autowired -> TODO (1). 아예 직접 생성. SpringConfig class.
    public MemberService(MemberRepository memberRepository) {
        // @Repository 붙어있는 구현체 찾아서 생성해줌
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // todo optional
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
    }

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
