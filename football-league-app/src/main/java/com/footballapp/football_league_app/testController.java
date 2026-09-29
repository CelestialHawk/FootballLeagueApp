package com.footballapp.football_league_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test";
    }
}
