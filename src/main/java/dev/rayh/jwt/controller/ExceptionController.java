package dev.rayh.jwt.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @ExceptionHandler(BadCredentialsException.class)
    public String handlebadCredentialsException(BadCredentialsException e){
        return e.getMessage();
    }
}
