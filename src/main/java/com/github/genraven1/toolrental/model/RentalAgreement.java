package com.github.genraven1.toolrental.model;

import com.github.genraven1.toolrental.util.ChargeUtil;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalAgreement {

    protected RentalAgreement(){}

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
        final double dailyRentalCharge = this.tool.getType().getCharge();
        builder.append("Daily Rental Charge: ").append(dailyRentalCharge).append(System.getProperty("line.separator"));

        // Print Charge Days
        final int chargeDays = ChargeUtil.getTotalChargeDays(this, dueDate);
        builder.append("Charge days: ").append(chargeDays).append(System.getProperty("line.separator"));

        // Print Pre-Discount Charge
        final double preDiscountCharge = (double) Math.round(dailyRentalCharge * chargeDays * 100) / 100D;
        builder.append("Pre-discount charge: ").append("/$").append(preDiscountCharge).append(System.getProperty("line.separator"));

        // Print Discount Percent
        final int discount = this.checkout.getDiscount();
        builder.append("Discount percent: ").append(discount).append("%%").append(System.getProperty("line.separator"));

        // Print Discount Amount
        final double discountPercent = (double) discount / 100;
        final double discountAmount = (double) Math.round(preDiscountCharge * discountPercent * 100) / 100;
        builder.append("Discount amount: ").append("/$").append(discountAmount).append(System.getProperty("line.separator"));

        // Print Final Charge
        final double finalCharge = preDiscountCharge - discountAmount;
        builder.append("Final charge: ").append("/$").append(finalCharge).append(System.getProperty("line.separator"));
        System.out.println(builder);
    }
}
