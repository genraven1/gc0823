package com.github.genraven1.toolrental.controller;

import com.github.genraven1.toolrental.exceptions.IllegalDiscountException;
import com.github.genraven1.toolrental.model.Checkout;
import com.github.genraven1.toolrental.model.RentalAgreement;
import com.github.genraven1.toolrental.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(final ToolService toolService) {
        this.toolService = toolService;
    }

    @PostMapping
    public ResponseEntity<RentalAgreement> generateRentalAgreement(@RequestBody final Checkout checkout) {
        // Checks if Discount is between 0 and 100 inclusively.
        validateDiscountPercent(checkout.getDiscount());

        return ResponseEntity.ok(toolService.generateRentalAgreement(checkout));
    }

    public void validateDiscountPercent(final int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalDiscountException(discount);
        }
    }
}
