package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MemberController {

    // 스프링 컨테이너에 등록을하고 컨테이너에서 가져와서 써야함, 여러 컨트롤러들이 MemberService를 가져다 쓸 수 있는데
    // 하나만 생성해서 공용으로 쓰면 됨
    //private final MemberService memberService = new MemberService();

    // 스프링 컨테이너에 등록
    private final MemberService memberService;

    // 스프링 컨테이너가 뜰때 MemberController 생성
    // 이때 생성자에 @Autowired가 되어있으면 맴버서비스를 스프링이 스프링 컨테이너에 있는 맴버서비스를 연결을 시켜줌 (생성자 주입)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 필드 주입 (예전에는 많이 사용했었지만 추천 하지 않음)
    //@Autowired
    //private MemberService memberService;

    // setter 주입
    // 단점 : 누군가가 컨트롤러를 호출했을 때 public으로 열려 있어야 함 / public하게 노출이 됨
    // memberService.setMemberRepository() 처럼 아무 개발자나 호출할 수 있게됨 / 최대한 호출하지 않아야 할 메소드는 안되는게 좋음
    /*@Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/
}
