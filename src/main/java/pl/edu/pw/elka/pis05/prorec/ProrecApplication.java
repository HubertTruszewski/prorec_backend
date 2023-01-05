package pl.edu.pw.elka.pis05.prorec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ProrecApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProrecApplication.class, args);
	}

}
