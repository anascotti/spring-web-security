package org.springframework.hello.api.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello/api/v1")
public class HelloController {

    @RequestMapping(value = "/greeting",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting getGreeting() {
        return new Greeting("Awesome");
    }
}
