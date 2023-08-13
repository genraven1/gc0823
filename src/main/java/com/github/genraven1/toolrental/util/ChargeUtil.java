package com.github.genraven1.toolrental.util;

import com.github.genraven1.toolrental.model.RentalAgreement;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

@Component
public class ChargeUtil {

    public static int getTotalChargeDays(final RentalAgreement agreement, final LocalDate dueDate) {
        int chargeDays = agreement.getCheckout().getDays();
        final LocalDate startDate = agreement.getCheckout().getCheckout();
        final List<LocalDate> rentalDays = startDate.datesUntil(dueDate).toList();
        if (agreement.getTool().getType().isHoliday()) {
            for (final LocalDate date : rentalDays) {
                if (includesIndependenceDayHoliday(date)) {
                    chargeDays -= 1;
                }

                if (includesLaborDayHoliday(date)) {
                    chargeDays -= 1;
                }

                if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    chargeDays -= 1;
                }
            }
        }
        return chargeDays;
    }

    private static boolean includesIndependenceDayHoliday(final LocalDate date) {
        return false;
    }

    private static boolean includesLaborDayHoliday(final LocalDate date) {
        final YearMonth september = YearMonth.of(date.getYear(), Month.SEPTEMBER);
        final LocalDate laborDay = september.atDay(1).with(DayOfWeek.MONDAY);
        System.out.println(laborDay);
        return date.isEqual(laborDay);
    }
}
