package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Integer id;

    @Column(name = "CREATED", nullable = false, length = 19)
    private LocalDateTime created;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "BUSINESS_TYPE", nullable = false, length = 64)
    private String businessType;

    @Column(name = "BUSINESS_NAME", nullable = false, length = 128)
    private String businessName;

    @Column(name = "OWNER_NAME", nullable = false, length = 128)
    private String ownerName;

    @Column(name = "EMAIL", nullable = false, length = 128)
    private String email;

    @Column(name = "PHONE", nullable = false, length = 16)
    private String phone;

    @Column(name = "SALES_TAX", nullable = false, length = 8)
    private String salesTax;

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = LocalDateTime.now();
        }
    }
}
