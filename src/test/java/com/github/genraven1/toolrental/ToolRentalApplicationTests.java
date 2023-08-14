package com.github.genraven1.toolrental;

import com.github.genraven1.toolrental.exceptions.IllegalDiscountException;
import com.github.genraven1.toolrental.model.Checkout;
import com.github.genraven1.toolrental.model.RentalAgreement;
import com.github.genraven1.toolrental.model.Tool;
import com.github.genraven1.toolrental.service.ToolService;
import com.github.genraven1.toolrental.util.ChargeUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
class ToolRentalApplicationTests {

    private static Tool tool1;
    private static Tool tool2;
    private static Tool tool3;
    private static Tool tool4;
    private static Checkout checkout1;
    private static Checkout checkout2;
    private static Checkout checkout3;
    private static Checkout checkout4;
    private static Checkout checkout5;
    private static Checkout checkout6;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    private final ToolService toolService = new ToolService();

    @BeforeAll
    static void init() {
        tool1 = new Tool("CHNS", Tool.Type.CHAINSAW, Tool.Brand.STIHL);
        tool2 = new Tool("LADW", Tool.Type.LADDER, Tool.Brand.WERNER);
        tool3 = new Tool("JAKD", Tool.Type.JACKHAMMER, Tool.Brand.DEWALT);
        tool4 = new Tool("JAKR", Tool.Type.JACKHAMMER, Tool.Brand.RIDGID);

        checkout1 = new Checkout("JAKR", 5, 101, LocalDate.of(2015, Month.SEPTEMBER, 3));
        checkout2 = new Checkout("LADW", 3, 10, LocalDate.of(2020, Month.JULY, 2));
        checkout3 = new Checkout("CHNS", 5, 25, LocalDate.of(2015, Month.JULY, 2));
        checkout4 = new Checkout("JAKD", 6, 0, LocalDate.of(2015, Month.SEPTEMBER, 3));
        checkout5 = new Checkout("JAKR", 9, 0, LocalDate.of(2015, Month.JULY, 2));
        checkout6 = new Checkout("JAKR", 4, 50, LocalDate.of(2020, Month.JULY, 2));
    }

    @Test
    public void testOrderOne() {
        assertThrows(IllegalDiscountException.class, () -> toolService.generateRentalAgreement(checkout1));
    }

    @Test
    public void testOrderTwo() {
        final RentalAgreement agreement = toolService.generateRentalAgreement(checkout2);
        printRentalAgreement(agreement);
    }

    @Test
    public void testOrderThree() {
        final RentalAgreement agreement = toolService.generateRentalAgreement(checkout3);
        printRentalAgreement(agreement);
    }

    @Test
    public void testOrderFour() {
        final RentalAgreement agreement = toolService.generateRentalAgreement(checkout4);
        printRentalAgreement(agreement);
    }

    @Test
    public void testOrderFive() {
        final RentalAgreement agreement = toolService.generateRentalAgreement(checkout5);
        printRentalAgreement(agreement);
    }

    @Test
    public void testOrderSix() {
        final RentalAgreement agreement = toolService.generateRentalAgreement(checkout6);
        printRentalAgreement(agreement);
    }

    private void printRentalAgreement(final RentalAgreement agreement) {
        final StringBuilder builder = new StringBuilder();
        // Print Tool Code
        builder.append("Tool Code: ").append(agreement.getTool().getCode()).append(System.getProperty("line.separator"));

        // Print Tool Type
        builder.append("Tool Type: ").append(agreement.getTool().getType().getLabel()).append(System.getProperty("line.separator"));

        // Print Tool Brand
        builder.append("Tool Brand: ").append(agreement.getTool().getBrand()).append(System.getProperty("line.separator"));

        // Print Rental Days
        builder.append("Rental Days: ").append(agreement.getCheckout().getDays()).append(System.getProperty("line.separator"));

        // Print Checkout Date
        builder.append("Check out date: ").append(formatLocalDate(agreement.getCheckout().getCheckout())).append(System.getProperty("line.separator"));

        // Print Due Date
        final LocalDate dueDate = agreement.getCheckout().getCheckout().plusDays(agreement.getCheckout().getDays());
        builder.append("Due date: ").append(formatLocalDate(dueDate)).append(System.getProperty("line.separator"));

        // Print Daily Rental Charge
        final double dailyRentalCharge = agreement.getTool().getType().getCharge();
        builder.append("Daily Rental Charge: ").append("$").append(dailyRentalCharge).append(System.getProperty("line.separator"));

        // Print Charge Days
        final int chargeDays = ChargeUtil.getTotalChargeDays(agreement, dueDate);
        builder.append("Charge days: ").append(chargeDays).append(System.getProperty("line.separator"));

        // Print Pre-Discount Charge
        final double preDiscountCharge = dailyRentalCharge * chargeDays;
        builder.append("Pre-discount charge: ").append("$").append(roundDouble(preDiscountCharge)).append(System.getProperty("line.separator"));

        // Print Discount Percent
        final int discount = agreement.getCheckout().getDiscount();
        builder.append("Discount percent: ").append(discount).append("%").append(System.getProperty("line.separator"));

        // Print Discount Amount
        final double discountPercent = (double) discount / 100;
        final double discountAmount = preDiscountCharge * discountPercent;
        builder.append("Discount amount: ").append("$").append(roundDouble(discountAmount)).append(System.getProperty("line.separator"));

        // Print Final Charge
        final double finalCharge = preDiscountCharge - discountAmount;
        builder.append("Final charge: ").append("$").append(roundDouble(finalCharge)).append(System.getProperty("line.separator"));
        System.out.println(builder);
    }

    /**
     * Correctly rounds up change to the correct number of cents.
     *
     * @param charge How much the rental costs.
     * @return The charge in dd.cc format.
     */
    private BigDecimal roundDouble(final double charge) {
        return new BigDecimal(charge).setScale(2, RoundingMode.HALF_UP);
    }

    private String formatLocalDate(final LocalDate date) {
        return date.format(formatter);
    }
}
