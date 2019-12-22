package com.bba.appt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatePeriodDto {

    private String period;
    private LocalDate start;
    private LocalDate end;
}
