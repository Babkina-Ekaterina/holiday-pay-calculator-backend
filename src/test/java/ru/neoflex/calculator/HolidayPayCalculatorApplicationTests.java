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
    @DisplayName("Вычисление отпускных по датам 1")
    void testCalculateHolidayPayByDates1() {
        // given
        double averageSalary = 30000;
        LocalDate holidayStartDate = LocalDate.of(2024, 7, 1);
        LocalDate holidayEndDate = LocalDate.of(2024, 7, 14);
        boolean isThereFiveWorkingDays = false;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, 0, holidayStartDate, holidayEndDate, isThereFiveWorkingDays);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(16050.96);
    }

    @Test
    @DisplayName("Вычисление отпускных по датам 2")
    void testCalculateHolidayPayByDates2() {
        // given
        double averageSalary = 40000;
        LocalDate holidayStartDate = LocalDate.of(2024, 8, 10);
        LocalDate holidayEndDate = LocalDate.of(2024, 8, 30);
        boolean isThereFiveWorkingDays = true;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, 0, holidayStartDate, holidayEndDate, isThereFiveWorkingDays);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(38473.28);
    }

    @Test
    @DisplayName("Вычисление отпускных по датам 3")
    void testCalculateHolidayPayByDates3() {
        // given
        double averageSalary = 35000;
        LocalDate holidayStartDate = LocalDate.of(2024, 7, 11);
        LocalDate holidayEndDate = LocalDate.of(2024, 8, 2);
        boolean isThereFiveWorkingDays = false;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, 0, holidayStartDate, holidayEndDate, isThereFiveWorkingDays);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(30666.67);
    }

    @Test
    @DisplayName("Отрицательная ЗП")
    void testNegativeAverageSalary() {
        // given
        double averageSalary = -35000;
        LocalDate holidayStartDate = LocalDate.of(2024, 7, 11);
        LocalDate holidayEndDate = LocalDate.of(2024, 8, 2);
        boolean isThereFiveWorkingDays = true;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, 0, holidayStartDate, holidayEndDate, isThereFiveWorkingDays);

        // then
        assertThat(holidayPayResponseDto)
                .isNull();
    }

    @Test
    @DisplayName("Неправильные даты")
    void testWrongDates() {
        // given
        double averageSalary = 35000;
        LocalDate holidayStartDate = LocalDate.of(2024, 8, 11);
        LocalDate holidayEndDate = LocalDate.of(2024, 7, 2);
        boolean isThereFiveWorkingDays = false;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, 0, holidayStartDate, holidayEndDate, isThereFiveWorkingDays);

        // then
        assertThat(holidayPayResponseDto)
                .isNull();
    }

    @Test
    @DisplayName("Вычисление отпускных по кол-ву дней 1")
    void testCalculateHolidayPayByDaysNumber1() {
        // given
        double averageSalary = 30000;
        int holidayDays = 21;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, holidayDays, null, null, false);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(21501.71);
    }

    @Test
    @DisplayName("Вычисление отпускных по кол-ву дней 2")
    void testCalculateHolidayPayByDaysNumber2() {
        // given
        double averageSalary = 40000;
        int holidayDays = 10;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, holidayDays, null, null, false);

        // then
        assertThat(holidayPayResponseDto.getVacationPay())
                .isEqualTo(13651.88);
    }

    @Test
    @DisplayName("Отрицательная ЗП")
    void testNegativeAverageSalaryByDaysNumber() {
        // given
        double averageSalary = -35000;
        int holidayDays = 10;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, holidayDays, null, null, false);

        // then
        assertThat(holidayPayResponseDto)
                .isNull();
    }

    @Test
    @DisplayName("Отрицательное кол-во дней отпуска")
    void testNegativeDaysNumber() {
        // given
        double averageSalary = 35000;
        int holidayDays = -10;
        HolidayPayCalculatorService holidayPayCalculatorService = new HolidayPayCalculatorService();

        // when
        HolidayPayResponseDto holidayPayResponseDto =
                holidayPayCalculatorService.calculateHolidayPay(averageSalary, holidayDays, null, null, false);

        // then
        assertThat(holidayPayResponseDto)
                .isNull();
    }
}
