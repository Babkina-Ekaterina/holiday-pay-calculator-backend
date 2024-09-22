package ru.neoflex.calculator.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.calculator.data.dto.HolidayPayResponseDto;
import ru.neoflex.calculator.service.HolidayPayCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/holiday_pay_calculator")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class HolidayPayCalculatorController {

    private final HolidayPayCalculatorService holidayPayCalculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<HolidayPayResponseDto> calculateHolidayPay(@RequestParam double averageSalary,
                                                                                 @RequestParam int holidayDaysNumber,
                                                                                 @RequestParam LocalDate holidayStartDate,
                                                                                 @RequestParam LocalDate holidayEndDate,
                                                                                 @RequestParam boolean isThereFiveWorkingDays) {
        HolidayPayResponseDto holidayPayResponseDto = holidayPayCalculatorService.calculateHolidayPay(averageSalary,
        holidayDaysNumber, holidayStartDate, holidayEndDate, isThereFiveWorkingDays);

        if (holidayPayResponseDto != null) {
            return new ResponseEntity<>(holidayPayResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
