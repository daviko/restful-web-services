package com.in28minutes.rest.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BasicAuthenticationController {

    @GetMapping(path = "/basicauth")
    public AuthenticationBean authenticationBean() {
        return new AuthenticationBean("You are authenticated");
    }

}
