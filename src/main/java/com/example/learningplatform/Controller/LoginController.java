package com.example.learningplatform.Controller;


import com.example.learningplatform.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    //// post to display sensitive data
    @PostMapping("/teacher/{username}/{password}")
    public ResponseEntity<?> teacherLogin(@PathVariable String username, @PathVariable String password) {

        String token = loginService.teacherLogin(username, password);
        return ResponseEntity.ok("Login successful your token is: " + token);
    }

    @PostMapping("/student/{username}/{password}")
    public ResponseEntity<?> studentLogin(@PathVariable String username, @PathVariable String password) {

        String token = loginService.studentLogin(username, password);
        return ResponseEntity.ok("Login successful your token is: " + token);
    }
}
