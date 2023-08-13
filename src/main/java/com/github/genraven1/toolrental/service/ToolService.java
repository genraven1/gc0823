package com.github.genraven1.toolrental.service;

import com.github.genraven1.toolrental.exceptions.IllegalDiscountException;
import com.github.genraven1.toolrental.exceptions.IllegalNumberOfRentalDaysException;
import com.github.genraven1.toolrental.model.Checkout;
import com.github.genraven1.toolrental.model.RentalAgreement;
import com.github.genraven1.toolrental.model.Tool;
import com.github.genraven1.toolrental.util.ChargeUtil;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
public class ToolService {

    /**
     * Generates a Rental Agreement for the Tool being checked out.
     *
     * @param checkout The information about the checkout for the tool.
     */
    public RentalAgreement generateRentalAgreement(final Checkout checkout) throws IllegalNumberOfRentalDaysException {
        final int discount = checkout.getDiscount();
        try {
            if (discount < 0 || discount > 100) {
                throw new RuntimeException(new IllegalDiscountException(discount));
            }
        } catch (final IllegalDiscountException exception) {
            throw new IllegalDiscountException(discount);
        }
        // Checks if Discount is between 0 and 100 inclusively.

        // Checks if number of Rental days is greater than 0
        if (checkout.getDays() < 1) {
            throw new IllegalNumberOfRentalDaysException();
        }
        final RentalAgreement agreement = new RentalAgreement(getToolByCode(checkout.getCode()), checkout);
        return agreement;
    }

    public List<Tool> getAllTools() {
        return Arrays.asList(
                new Tool("CHNS", Tool.Type.CHAINSAW, Tool.Brand.STIHL),
                new Tool("LADW", Tool.Type.LADDER, Tool.Brand.WERNER),
                new Tool("JAKD", Tool.Type.JACKHAMMER, Tool.Brand.DEWALT),
                new Tool("JAKR", Tool.Type.JACKHAMMER, Tool.Brand.RIDGID)
        );
    }
    
    public Tool getToolByCode(final String code) {
        final List<Tool> tools = getAllTools();
        Tool foundTool = null;
        for (final Tool tool : tools) {
            if (tool.getCode().equals(code)) {
                foundTool = tool;
            }
        }
        return foundTool;
    }
}
