package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bb_payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TX_DATE", nullable = false)
    private LocalDate txDate;

    @Column(name = "TYPE", length = 1, nullable = false)
    private String type;

    @Column(name = "REF_NUM", length = 16, nullable = false)
    private String refNum;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "PURCHASE_ID", nullable = false)
    private Integer purchaseId;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "REGISTER_ID")
    private Integer registerId;
}
