package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_reminder")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REMINDER_ID")
    private Integer id;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Column(name = "DUE_ON")
    private LocalDate dueOn;

    @Column(name = "SUBJECT", length = 128)
    private String subject;

    @Column(name = "URL", length = 128)
    private String url;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = LocalDateTime.now();
        }
    }
}
