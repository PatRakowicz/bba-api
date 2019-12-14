package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bb_sale_payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalePaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID")
    private Integer id;

    @Column(name = "TX_DATE", nullable = false)
    private LocalDate txDate;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "REF_NUM", nullable = false, length = 16)
    private String refNum;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "DEPOSITED", nullable = false)
    private Boolean deposited;

    @Column(name = "NOTE", length = 64)
    private String note;

    @Column(name = "SALE_ID", nullable = false)
    private Integer saleId;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "REGISTER_ID")
    private Integer registerId;
}
