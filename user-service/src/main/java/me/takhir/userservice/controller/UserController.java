package me.takhir.userservice.controller;

import me.takhir.userservice.model.User;
import me.takhir.userservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getUser(userId));
    }

    @PostMapping("/withdraw/{userId}")
    public ResponseEntity<HttpStatus> getUser(@PathVariable Long userId, @RequestBody Double amount) {
        return ResponseEntity.ok(paymentService.pay(userId, amount));
    }
}
