package me.takhir.paymentservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private Double balance;
}
