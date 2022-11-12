package pl.edu.pw.elka.pis05.prorec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello world!";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloWithName(@PathVariable final String name) {
        return "Hello " + name + "!";
    }
}
