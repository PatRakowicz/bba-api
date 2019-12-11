package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_client_referral")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientReferralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "CLIENT_ID", nullable = false)
    private Integer clientId;

    @Column(name = "REF_CLIENT_ID", nullable = false)
    private Integer refClientId;

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = LocalDateTime.now();
        }
    }
}
