package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bb_bill")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BILL_ID")
    private Integer id;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "DESCRIPTION", nullable = false, length = 128)
    private String description;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "REPEAT_TYPE", nullable = false, length = 1)
    private String repeatType;

    @Column(name = "REPEAT_PATTERN")
    private String repeatPattern;

    @Column(name = "NEXT_DUE_DATE", nullable = false)
    private LocalDate nextDueDate;

    @Column(name = "PAY_TYPE", nullable = false, length = 1)
    private String payType;

    @Column(name = "PAY_REF_NUM", nullable = false, length = 16)
    private String payRefNum;

    @Column(name = "GEN_REMINDER", nullable = false)
    private Boolean genReminder;

    @Column(name = "GEN_PURCHASE", nullable = false)
    private Boolean genPurchase;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "VENDOR_ID", nullable = false)
    private Integer vendorId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Integer categoryId;
}
