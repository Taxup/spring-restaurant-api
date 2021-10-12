package me.takhir.paymentservice.controller;

import me.takhir.paymentservice.model.PayForDishDto;
import me.takhir.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/buy")
    public ResponseEntity<HttpStatus> buy(@RequestBody PayForDishDto payForDishDto) {
        return ResponseEntity.ok(paymentService.pay(payForDishDto));
    }
}
