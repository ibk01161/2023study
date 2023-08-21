package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		System.out.print("구동시작");
		SpringApplication.run(HelloSpringApplication.class, args);
		System.out.print("구동완료");
	}

}
