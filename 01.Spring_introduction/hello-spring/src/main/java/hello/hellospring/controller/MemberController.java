package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // 스프링 컨테이너에 등록을하고 컨테이너에서 가져와서 써야함, 여러 컨트롤러들이 MemberService를 가져다 쓸 수 있는데
    // 하나만 생성해서 공용으로 쓰면 됨
    //private final MemberService memberService = new MemberService();

    // 스프링 컨테이너에 등록
    private final MemberService memberService;

    // 스프링 컨테이너가 뜰때 MemberController 생성
    // 이때 생성자에 @Autowired가 되어있으면 맴버서비스를 스프링이 스프링 컨테이너에 있는 맴버서비스를 연결을 시켜줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
