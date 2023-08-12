package com.github.genraven1.toolrental.util;

import com.github.genraven1.toolrental.model.RentalAgreement;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class ChargeUtil {

    public static int getTotalChargeDays(final RentalAgreement agreement, final LocalDate dueDate) {
        final LocalDate startDate = agreement.getCheckout().getCheckout();
        final List<LocalDate> rentalDays = startDate.datesUntil(dueDate).toList();
        if (agreement.getTool().getType().isHoliday()) {
            for (final LocalDate date : rentalDays) {
                if (date.equals(LocalDate.of(date.getYear(), Month.JULY, 4))) {

                }
            }
        }
        int chargeDays = agreement.getCheckout().getDays();
        return chargeDays;
    }

    private static void removeWeekendChargeDays(final RentalAgreement agreement, final LocalDate dueDate, int chargeDays) {
        LocalDate startDate = agreement.getCheckout().getCheckout();
        while (dueDate.isAfter(startDate)) {
            if (startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                chargeDays -= 1;
            }
            startDate = startDate.plusDays(1);
        }
    }

    private static void removeHolidayChargeDays(final RentalAgreement agreement, final LocalDate dueDate) {

    }
}
