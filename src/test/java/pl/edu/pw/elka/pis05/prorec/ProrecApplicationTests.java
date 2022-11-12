package pl.edu.pw.elka.pis05.prorec;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest
class ProrecApplicationTests {

    @Autowired
    private HelloController controller;

    @Test
	void contextLoads() {
        assertThat(controller).isNotNull();
	}

    @Test
    void sayHelloTest() {
        assertThat(controller.sayHello()).isEqualTo("Hello world!");
    }

    @Test
    void sayHelloName() {
        assertThat(controller.sayHelloWithName("John")).isEqualTo("Hello John!");
    }
}
