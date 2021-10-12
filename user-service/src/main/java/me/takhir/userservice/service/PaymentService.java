package me.takhir.userservice.service;

import me.takhir.userservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

public interface PaymentService {
    User getUser(Long userId);
    HttpStatus pay(Long userId, Double amount);
}

@Service
class PaymentServiceImpl implements PaymentService {

    @Override
    public User getUser(Long userId) {
        return new User(1L, "Takhir", 100000.0);
    }

    @Override
    public HttpStatus pay(Long userId, Double amount) {
        return HttpStatus.OK;
    }
}
