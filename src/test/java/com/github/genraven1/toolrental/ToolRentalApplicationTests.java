package com.github.genraven1.toolrental;

import com.github.genraven1.toolrental.exceptions.IllegalDiscountException;
import com.github.genraven1.toolrental.model.Checkout;
import com.github.genraven1.toolrental.model.Tool;
import com.github.genraven1.toolrental.service.ToolService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

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

    private final ToolService toolService = mock(ToolService.class);

    @BeforeAll
    static void setup() {
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
        Mockito.when(toolService.generateRentalAgreement(checkout1)).thenThrow(IllegalDiscountException.class);
        assertThrows(IllegalDiscountException.class, () -> toolService.generateRentalAgreement(checkout1));
    }

    @Test
    public void testOrderTwo() {

    }

    @Test
    public void testOrderThree() {

    }

    @Test
    public void testOrderFour() {

    }

    @Test
    public void testOrderFive() {

    }

    @Test
    public void testOrderSix() {

    }
}
