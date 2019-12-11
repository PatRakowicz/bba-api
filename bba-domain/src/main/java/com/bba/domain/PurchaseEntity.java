package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bb_purchase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_ID")
    private Integer id;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "TX_DATE", length = 10)
    private LocalDate txDate;

    @Column(name = "TX_REF_NUM", length = 16)
    private String txRefNum;

    @Column(name = "DUE_DATE", length = 10)
    private LocalDate dueDate;

    @Column(name = "TOTAL", length = 9)
    private BigDecimal total;

    @Column(name = "PAID", nullable = false, length = 1)
    private Boolean paid;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "VENDOR_ID", nullable = false)
    private Integer vendorId;
}
