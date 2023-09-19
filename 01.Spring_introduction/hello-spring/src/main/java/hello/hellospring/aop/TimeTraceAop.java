package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    // TimeTraceAop를 스프링 빈에 등록을 시켜야 함
    // 1. 상단에 @Component를 사용하는 방법
    // 2. SpringConfig에 @Bean으로 등록하는 방법

    // 가짜 memberService를 만듬(프록시), 가짜 스프링 빈을 앞에 태움, 끝나면 joinPoint.proceed() 하면 진짜 memberService를 호출함
    @Around("execution(* hello.hellospring..*(..))")            // hello.hellospring 패키지 하위에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
