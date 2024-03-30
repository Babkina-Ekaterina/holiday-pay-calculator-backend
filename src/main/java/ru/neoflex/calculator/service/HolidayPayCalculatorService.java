package ru.neoflex.calculator.service;

import lombok.AllArgsConstructor;
import ru.neoflex.calculator.data.dto.HolidayPayResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class HolidayPayCalculatorService {

    public HolidayPayResponseDto calculateHolidayPay(double averageSalary,
                                                     LocalDate holidayStartDate,
                                                     LocalDate holidayEndDate) {
        if (averageSalary < 0 || holidayStartDate == null || holidayEndDate == null ||
                holidayStartDate.isAfter(holidayEndDate)) {
            return null;
        }

        int workingDays = 0;
        int holidayDays = (int) ChronoUnit.DAYS.between(holidayStartDate, holidayEndDate) + 1;
        LocalDate currentDate = holidayStartDate.minusYears(1);

        while (!currentDate.isAfter(holidayStartDate)) {
            if (currentDate.getDayOfWeek().getValue() < 7) {
                workingDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        double holidayPay = Math.round((averageSalary * 12 / workingDays * holidayDays) * 100.0) / 100.0;

        return new HolidayPayResponseDto(holidayPay);
    }
}
