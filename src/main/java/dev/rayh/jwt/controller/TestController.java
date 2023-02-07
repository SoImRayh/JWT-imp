package dev.rayh.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/teste")
public class TestController {

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/adminonly")
    public String onlyADM(){
        return "onlyADMIN";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/useronly")
    public String userOnly(){
        return "user Only";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/all")
    public String all(){
        return "all";
    }
}
