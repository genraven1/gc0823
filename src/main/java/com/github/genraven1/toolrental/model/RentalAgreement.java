package com.github.genraven1.toolrental.model;

import lombok.Data;

@Data
public class RentalAgreement {

    protected RentalAgreement(){}

    public RentalAgreement(final Tool tool, final Checkout checkout) {
        this.tool = tool;
        this.checkout = checkout;
    }

    private Tool tool;

    private Checkout checkout;
}
