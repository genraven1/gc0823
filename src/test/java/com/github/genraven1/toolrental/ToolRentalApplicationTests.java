package com.github.genraven1.toolrental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.genraven1.toolrental.model.Checkout;
import com.github.genraven1.toolrental.model.Tool;
import com.github.genraven1.toolrental.service.ToolService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ToolRentalApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolService service;

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
    public void testOrderOne() throws Exception {
        this.mockMvc.perform(post("/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertCheckoutToJsonString(checkout1)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testOrderTwo() throws Exception {
        this.mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertCheckoutToJsonString(checkout2)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testOrderThree() throws Exception {
        this.mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertCheckoutToJsonString(checkout3)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testOrderFour() throws Exception {
        this.mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertCheckoutToJsonString(checkout4)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testOrderFive() throws Exception {
        this.mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertCheckoutToJsonString(checkout5)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testOrderSix() throws Exception {
        this.mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertCheckoutToJsonString(checkout6)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    static String convertCheckoutToJsonString(final Checkout checkout) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(checkout);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
