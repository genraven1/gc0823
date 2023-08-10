package com.github.genraven1.toolrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Checkout {
    private String code;

    private int days;

    private int discount;

    private LocalDate checkout;
}
