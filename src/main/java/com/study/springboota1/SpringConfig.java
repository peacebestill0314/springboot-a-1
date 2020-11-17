package com.study.springboota1;

import com.study.springboota1.repository.MemberRepository;
import com.study.springboota1.repository.MemoryMemberRepository;
import com.study.springboota1.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO (2). 빈 직접 등록. 장단점이 있다.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
