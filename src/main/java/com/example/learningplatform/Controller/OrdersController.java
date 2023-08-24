package com.example.learningplatform.Controller;

import com.example.learningplatform.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;


    @PostMapping("/buy/{courseID}/{studentToken}")
    public ResponseEntity<?> buyCourse(@PathVariable Integer courseID, @PathVariable String studentToken) {


        return ResponseEntity.ok(orderService.buyCourse(courseID, studentToken));
    }
}
