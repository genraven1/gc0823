package com.github.genraven1.toolrental.service;

import com.github.genraven1.toolrental.exceptions.IllegalDiscountException;
import com.github.genraven1.toolrental.exceptions.IllegalNumberOfRentalDaysException;
import com.github.genraven1.toolrental.exceptions.ToolNotFoundException;
import com.github.genraven1.toolrental.model.Checkout;
import com.github.genraven1.toolrental.model.RentalAgreement;
import com.github.genraven1.toolrental.model.Tool;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

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
        // Checks if Discount is between 0 and 100 inclusively.
        if (discount < 0 || discount > 100) {
            throw new IllegalDiscountException(discount);
        }

        // Checks if number of Rental days is greater than 0
        if (checkout.getDays() < 1) {
            throw new IllegalNumberOfRentalDaysException();
        }

        return new RentalAgreement(getToolByCode(checkout.getCode()), checkout);
    }

    /**
     * Creates a list of all tools in the system.
     *
     * @return A list of all the tools in the system.
     */
    public List<Tool> getAllTools() {
        return Arrays.asList(
                new Tool("CHNS", Tool.Type.CHAINSAW, Tool.Brand.STIHL),
                new Tool("LADW", Tool.Type.LADDER, Tool.Brand.WERNER),
                new Tool("JAKD", Tool.Type.JACKHAMMER, Tool.Brand.DEWALT),
                new Tool("JAKR", Tool.Type.JACKHAMMER, Tool.Brand.RIDGID)
        );
    }

    /**
     * Gets a tool based on the unique tool code associated with each tool in the system.
     *
     * @param code The Unique Tool Code.
     * @return The tool associated with the Tool Code.
     */
    public Tool getToolByCode(final String code) {
        return getAllTools().stream().filter(tool -> tool.getCode().equals(code)).findFirst().orElseThrow(() -> new ToolNotFoundException(code));
    }
}
