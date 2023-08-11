package com.github.genraven1.toolrental.service;

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
     * @return The Rental Agreement for that tool and checkout
     */
    public RentalAgreement generateRentalAgreement(final Checkout checkout) {
        final RentalAgreement agreement = new RentalAgreement(getToolByCode(checkout.getCode()), checkout);
        agreement.printRentalAgreement();
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
