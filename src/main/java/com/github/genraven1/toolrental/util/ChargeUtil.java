package com.github.genraven1.toolrental.util;

import com.github.genraven1.toolrental.model.RentalAgreement;
import com.github.genraven1.toolrental.model.Tool;
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
        final Tool.Type toolType = agreement.getTool().getType();
        final List<LocalDate> rentalDays = agreement.getCheckout().getCheckout().datesUntil(dueDate).toList();
        for (final LocalDate date : rentalDays) {
            if (!toolType.isHoliday() && includesIndependenceDayHoliday(date)) {
                chargeDays -= 1;
            }

            if (!toolType.isHoliday() && includesLaborDayHoliday(date)) {
                chargeDays -= 1;
            }

            if (!toolType.isWeekend() && includesWeekend(date)) {
                chargeDays -= 1;
            }

            if (!toolType.isWeekday() && !includesWeekend(date)) {
                chargeDays -= 1;
            }
        }
        return chargeDays;
    }

    private static boolean includesIndependenceDayHoliday(final LocalDate date) {
        final LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        switch (independenceDay.getDayOfWeek()) {
            case SATURDAY -> {
                return independenceDay.minusDays(1).isEqual(date);
            }
            case SUNDAY -> {
                return independenceDay.plusDays(1).isEqual(date);
            }
            default -> {
                return independenceDay.isEqual(date);
            }
        }
    }

    private static boolean includesLaborDayHoliday(final LocalDate date) {
        final YearMonth september = YearMonth.of(date.getYear(), Month.SEPTEMBER);
        LocalDate laborDay = september.atDay(1);
        while (!laborDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            laborDay = laborDay.plusDays(1);
        }
        return date.isEqual(laborDay);
    }

    private static boolean includesWeekend(final LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }
}
