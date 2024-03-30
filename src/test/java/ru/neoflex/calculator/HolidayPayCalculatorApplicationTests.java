package ru.neoflex.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.calculator.data.dto.HolidayPayResponseDto;
import ru.neoflex.calculator.service.HolidayPayCalculatorService;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;

@SpringBootTest
class HolidayPayCalculatorApplicationTests {

    @Test
    @DisplayName("Вычисление отпускных 1")
    void testCalculateHolidayPay1() {
        // given
        double averageSalary = 30000;
        LocalDate vacationStartDate = LocalDate.of(2024, 7, 1);
        LocalDate vacationEndDate = LocalDate.of(2024, 7, 14);
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, vacationStartDate, vacationEndDate);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(16050.96);
    }

    @Test
    @DisplayName("Вычисление отпускных 2")
    void testCalculateHolidayPay2() {
        // given
        double averageSalary = 40000;
        LocalDate vacationStartDate = LocalDate.of(2024, 8, 10);
        LocalDate vacationEndDate = LocalDate.of(2024, 8, 30);
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, vacationStartDate, vacationEndDate);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(32000);
    }

    @Test
    @DisplayName("Вычисление отпускных 3")
    void testCalculateHolidayPay3() {
        // given
        double averageSalary = 35000;
        LocalDate vacationStartDate = LocalDate.of(2024, 7, 11);
        LocalDate vacationEndDate = LocalDate.of(2024, 8, 2);
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, vacationStartDate, vacationEndDate);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(30666.67);
    }

    @Test
    @DisplayName("Отрицательная ЗП")
    void testNegativeAverageSalary() {
        // given
        double averageSalary = -35000;
        LocalDate vacationStartDate = LocalDate.of(2024, 7, 11);
        LocalDate vacationEndDate = LocalDate.of(2024, 8, 2);
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, vacationStartDate, vacationEndDate);

        // then
        assertThat(holidayPayResponseDto)
                .isNull();
    }

    @Test
    @DisplayName("Неправильные даты")
    void testWrongDates() {
        // given
        double averageSalary = 35000;
        LocalDate vacationStartDate = LocalDate.of(2024, 8, 11);
        LocalDate vacationEndDate = LocalDate.of(2024, 7, 2);
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, vacationStartDate, vacationEndDate);

        // then
        assertThat(holidayPayResponseDto)
                .isNull();
    }

    @Test
    @DisplayName("Даты равны null")
    void testNullDates() {
        // given
        double averageSalary = 35000;
        LocalDate vacationStartDate = null;
        LocalDate vacationEndDate = null;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, vacationStartDate, vacationEndDate);

        // then
        assertThat(holidayPayResponseDto)
                .isNull();
    }
}
