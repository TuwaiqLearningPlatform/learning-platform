package com.example.learningplatform.Controller;

import com.example.learningplatform.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;


    @GetMapping("/buy/{courseID}/{studentToken}")
    public ResponseEntity<?> buyCourse(@PathVariable Integer courseID, @PathVariable String studentToken) {


        return ResponseEntity.ok(orderService.buyCourse(courseID, studentToken));
    }
}
