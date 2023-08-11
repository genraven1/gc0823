package com.github.genraven1.toolrental.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalAgreement {

    public RentalAgreement(final Tool tool, final Checkout checkout) {
        this.tool = tool;
        this.checkout = checkout;
    }

    private Tool tool;

    private Checkout checkout;

    public void printRentalAgreement() {
        final StringBuilder builder = new StringBuilder();
        // Print Tool Code
        builder.append("Tool Code: ").append(this.tool.getCode()).append(System.getProperty("line.separator"));
        // Print Tool Type
        builder.append("Tool Type: ").append(this.tool.getType().getLabel()).append(System.getProperty("line.separator"));
        // Print Tool Brand
        builder.append("Tool Brand: ").append(this.tool.getBrand()).append(System.getProperty("line.separator"));
        // Print Rental Days
        builder.append("Rental Days: ").append(this.checkout.getDays()).append(System.getProperty("line.separator"));
        // Print Checkout Date
        builder.append("Check out date: ").append(this.checkout.getCheckout()).append(System.getProperty("line.separator"));
        // Print Due Date
        final LocalDate dueDate = this.checkout.getCheckout().plusDays(this.checkout.getDays());
        builder.append("Due date: ").append(dueDate).append(System.getProperty("line.separator"));
        // Print Daily Rental Charge
        builder.append("Daily Rental Charge: ").append(this.tool.getType().getCharge()).append(System.getProperty("line.separator"));
        // Print Charge Days
        builder.append("Charge days: ").append("").append(System.getProperty("line.separator"));
        // Print Pre-Discount Charge
        builder.append("Pre-discount charge: ").append("").append(System.getProperty("line.separator"));
        // Print Discount Percent
        builder.append("Discount percent: ").append("").append(System.getProperty("line.separator"));
        // Print Discount Amount
        builder.append("Discount amount: ").append("").append(System.getProperty("line.separator"));
        // Print Final Charge
        builder.append("Final charge: ").append("").append(System.getProperty("line.separator"));
        System.out.println(builder);
    }
}
