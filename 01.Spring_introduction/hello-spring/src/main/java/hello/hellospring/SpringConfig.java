package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    
    // 2023.09.05, 6-2 DataSource는 스프링이 제공
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 스프링이 뜰때 @Configuration을 읽고, memberService 메소드의 로직을 호출을 해서 스프링 빈에 등록해줌
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        // 2023.09.05, 6-2 JdbcMemberRepository를 스프링 빈으로 등록하게 변경
        return new JdbcMemberRepository(dataSource);
    }

}
