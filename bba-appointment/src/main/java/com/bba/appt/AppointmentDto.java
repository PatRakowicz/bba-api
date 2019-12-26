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
    private LocalDateTime startTime;
    private Integer length;
    private String subject;
    private String note;
    private String confirmed;

    private ClientDto client;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClientDto {

        private Integer id;
        private String name;
        private String phone;
    }
}
