package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_appointment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "START_TIME", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "LENGTH", nullable = false, length = 5)
    private String length;

    @Column(name = "SUBJECT", length = 128)
    private String subject;

    @Column(name = "NOTE", nullable = false)
    private String note;

    @Column(name = "CONFIRMED", length = 1)
    private String confirmed;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "CLIENT_ID")
    private Integer clientId;
}
