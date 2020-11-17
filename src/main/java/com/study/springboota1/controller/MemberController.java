package com.study.springboota1.controller;

import com.study.springboota1.domain.Member;
import com.study.springboota1.domain.MemberForm;
import com.study.springboota1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// controller는 Spring container가 뜰 때 component-scan으로 최초 생성하게 됨
@Controller
public class MemberController {

    // TODO
    // MemberService를 new로 생성할 경우 다른 컨트롤러에서도 계속 서비스를 생성해서 사용하게됨
    // 이를 방지하여 스프링 컨테이너에 빈으로 등록하여 하나로 쓸 수 있도록 해보자.

    // TODO
    private MemberService memberService;

    @Autowired
    // 생성자에 Autowired(연결)하면 MemberService 가져와서 넣어줌. 그런데! 멤버 서비스가 순수 자바 클래스. 스프링이 알 수가 없음. 스프링이 관리 할 수 있게 @Service로 어노테이션을 달아서 빈으로 등록가능하게 해줘라
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberlist(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
