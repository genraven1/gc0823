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
public class ChargeDaysUtil {

    /**
     * Takes the number of days being charged on the Rental Agreement and reduces them by when the tool can be charged.
     *
     * @param agreement The Rental Agreement for the Tool.
     * @param dueDate   The date in which the tool is due to be returned.
     * @return The number of days that charges will be applied for renting the given tool.
     */
    public static int getTotalChargeDays(final RentalAgreement agreement, final LocalDate dueDate) {
        int chargeDays = agreement.getCheckout().getDays();
        final Tool.Type toolType = agreement.getTool().getType();
        final List<LocalDate> rentalDays = agreement.getCheckout().getCheckout().datesUntil(dueDate).toList();
        for (final LocalDate date : rentalDays) {
            //Checks if Tool is charged for holidays and if the date is the Independence holiday date.
            if (!toolType.isHoliday() && includesIndependenceDayHoliday(date)) {
                chargeDays -= 1;
            }

            //Checks if Tool is charged for holidays and if the date is the Labor Day holiday date.
            if (!toolType.isHoliday() && includesLaborDayHoliday(date)) {
                chargeDays -= 1;
            }

            //Checks if Tool is charged for weekends and if the date is on the weekend.
            if (!toolType.isWeekend() && includesWeekend(date)) {
                chargeDays -= 1;
            }

            //Checks if Tool is charged for weekdays and if the date is not on a weekend.
            if (!toolType.isWeekday() && !includesWeekend(date)) {
                chargeDays -= 1;
            }
        }
        return chargeDays;
    }

    /**
     * Determines if the given LocalDate is on Independence Day.
     *
     * @param date The LocalDate that is being checked.
     * @return If the LocalDate is on the Independence Day Holiday.
     */
    private static boolean includesIndependenceDayHoliday(final LocalDate date) {
        final LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        switch (independenceDay.getDayOfWeek()) {
            // If Independence Day lands on Saturday, the Friday before is the holiday date.
            case SATURDAY -> {
                return independenceDay.minusDays(1).isEqual(date);
            }
            // If Independence Day lands on Sunday, the Monday after is the holiday date.
            case SUNDAY -> {
                return independenceDay.plusDays(1).isEqual(date);
            }
            // If Independence Day does not land on a weekend, the holiday date is July 4th.
            default -> {
                return independenceDay.isEqual(date);
            }
        }
    }

    /**
     * Determines if the given LocalDate is on Labor Day.
     *
     * @param date The LocalDate that is being checked.
     * @return If the LocalDate is on Labor Day (1st Monday in September).
     */
    private static boolean includesLaborDayHoliday(final LocalDate date) {
        final YearMonth september = YearMonth.of(date.getYear(), Month.SEPTEMBER);
        LocalDate laborDay = september.atDay(1);
        // Finding the first Monday after the start of September
        while (!laborDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            laborDay = laborDay.plusDays(1);
        }
        return date.isEqual(laborDay);
    }

    /**
     * Determines if the given LocalDate is on a Saturday or Sunday (weekend dates).
     *
     * @param date The LocalDate that is being checked.
     * @return If the LocalDate is on Saturday or Sunday.
     */
    private static boolean includesWeekend(final LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }
}
