package ru.neoflex.calculator.service;

import lombok.AllArgsConstructor;
import ru.neoflex.calculator.data.dto.HolidayPayResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class HolidayPayCalculatorService {
    private HolidayPayResponseDto calculateHolidayPayByDaysNumber(double averageSalary,
                                                                 int holidayDaysNumber) {
        if (averageSalary <= 0 || holidayDaysNumber <= 0) {
            return null;
        }

        double holidayPay = Math.round((averageSalary / 29.3 * holidayDaysNumber) * 100.0) / 100.0;

        return new HolidayPayResponseDto(holidayPay);
    }

    private HolidayPayResponseDto calculateHolidayPayByDates(double averageSalary,
                                                     LocalDate holidayStartDate,
                                                     LocalDate holidayEndDate,
                                                     boolean isThereFiveWorkingDays) {
        if (averageSalary <= 0 || holidayStartDate == null || holidayEndDate == null ||
                holidayStartDate.isAfter(holidayEndDate)) {
            return null;
        }

        int workingDays = 0;
        int holidayDays = (int) ChronoUnit.DAYS.between(holidayStartDate, holidayEndDate) + 1;
        LocalDate currentDate = holidayStartDate.minusYears(1);

        int dayOfWeek = isThereFiveWorkingDays ? 6 : 7;
        while (!currentDate.isAfter(holidayStartDate)) {
            if (currentDate.getDayOfWeek().getValue() < dayOfWeek) {
                workingDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        double holidayPay = Math.round((averageSalary * 12 / workingDays * holidayDays) * 100.0) / 100.0;

        return new HolidayPayResponseDto(holidayPay);
    }

    public HolidayPayResponseDto calculateHolidayPay(double averageSalary, int holidayDaysNumber,
                                                     LocalDate holidayStartDate, LocalDate holidayEndDate,
                                                     boolean isThereFiveWorkingDays) {
        HolidayPayResponseDto holidayPayResponseDto;
        if (holidayStartDate == null || holidayEndDate == null) {
            holidayPayResponseDto = calculateHolidayPayByDaysNumber(
                    averageSalary, holidayDaysNumber);
        } else {
            holidayPayResponseDto = calculateHolidayPayByDates(
                    averageSalary, holidayStartDate, holidayEndDate, isThereFiveWorkingDays);
        }
        return holidayPayResponseDto;
    }
}
