package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_client_note")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CREATED", nullable = false, length = 19)
    private LocalDateTime created;

    @Column(name = "NOTE", nullable = false)
    private String note;

    @Column(name = "CLIENT_ID", nullable = false)
    private Integer clientId;
}
