package me.takhir.paymentservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.takhir.paymentservice.model.PayForDishDto;
import me.takhir.paymentservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface PaymentService {
    HttpStatus pay(PayForDishDto payForDishDto);
}

@Service
class PaymentServiceImpl implements PaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(
            fallbackMethod = "payFallback",
            threadPoolKey = "pay",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50"),
            }
    )
    public HttpStatus pay(PayForDishDto payForDishDto) {
        User user = restTemplate.getForEntity("http://user-service/user/" + payForDishDto.getCustomerId(), User.class).getBody();
        if (user == null) throw new AssertionError();
        if (user.getBalance() >= payForDishDto.getPrice()) {
            HttpStatus status = restTemplate.postForObject("http://user-service/withdraw/" + payForDishDto.getCustomerId(), payForDishDto.getPrice(), HttpStatus.class);
            return status;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus payFallback(PayForDishDto payForDishDto) {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
