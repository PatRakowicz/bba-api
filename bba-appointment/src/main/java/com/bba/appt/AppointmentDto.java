package com.bba.appt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Integer id;
    private String name;
    private LocalDateTime start;
    private Integer len;
    private String subject;
    private String note;
    private String conf;
    private Integer cid;

}
