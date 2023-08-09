package com.github.genraven1.toolrental.service;

import com.github.genraven1.toolrental.model.Checkout;
import com.github.genraven1.toolrental.model.RentalAgreement;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ToolService {

    /**
     * Generates a Rental Agreement for the Tool being checked out.
     * @param checkout The information about the checkout for the tool.
     * @return The Rental Agreement for that tool and checkout
     */
    public RentalAgreement generateRentalAgreement(final Checkout checkout) {
        return new RentalAgreement();
    }
}
