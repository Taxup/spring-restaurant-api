package me.takhir.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayForDishDto {
    private Long customerId;
    private Double price;
}
