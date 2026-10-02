package com.footballapp.football_league_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Test port 8080 displays test
// Placeholder
@RestController
public class testController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test";
    }
}
