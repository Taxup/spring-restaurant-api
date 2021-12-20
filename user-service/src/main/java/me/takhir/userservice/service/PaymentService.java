package me.takhir.userservice.service;

import me.takhir.userservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface PaymentService {
    User getUser(Long userId);
    HttpStatus pay(Long userId, Double amount);
}

@Service
class PaymentServiceImpl implements PaymentService {

    private List<User> users = new ArrayList<User>() {{
        add(new User(1L, "Takhir", 100000.0));
        add(new User(2L, "Aralbaev", 1500000.0));
    }};

    public User getUser(Long userId) {
        User user = null;
        for (User u: users) {
            if (userId.equals(u.getId())) {
                user = u;
                break;
            }
        }
        return user;
    }

    public HttpStatus pay(Long userId, Double amount) {
        User user = getUser(userId);
        user.setBalance(user.getBalance() - amount);
        return HttpStatus.OK;
    }
}
