package com.github.genraven1.toolrental.service;

import com.github.genraven1.toolrental.model.RentalAgreement;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@NoArgsConstructor
public class ToolService {

    /**
     * Generates a Rental Agreement for the Tool being checked out.
     * @param code The unique tool code.
     * @param days The number of days that a customer wants to rent a tool.
     * @param discount The percent of discount applied to the item.
     * @param checkout When the customer wants to check out the tool.
     * @return
     */
    public RentalAgreement generateRentalAgreement(final String code, final int days, final int discount, final Date checkout) {
        return new RentalAgreement();
    }
}
