package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bb_client_reminder")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientReminderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REMINDER_ID")
    private Integer id;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "FREQUENCY", nullable = false)
    private Integer frequency;

    @Column(name = "CLIEND_ID", nullable = false)
    private Integer clientId;
}
