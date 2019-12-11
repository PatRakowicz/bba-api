package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bb_cash_register")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGISTER_ID")
    private Integer id;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    @Column(name = "TX_DESC", nullable = false, length = 128)
    private String txDesc;

    @Column(name = "TX_DATE", nullable = false)
    private LocalDate txDate;

    @Column(name = "TX_AMOUNT", nullable = false)
    private BigDecimal txAmount;

    @Column(name = "TX_NOTE", length = 128)
    private String txNote;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "BANK_ID", nullable = false)
    private Integer bankId;
}
