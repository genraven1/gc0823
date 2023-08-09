package com.github.genraven1.toolrental.model;

import lombok.Data;

import java.util.Date;

@Data
public class RentalAgreement {

    private Tool tool;

    private int days;

    private Date checkout;

    public void printRentalAgreement() {

    }
}
