package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bb_sale")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALE_ID")
    private Integer id;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "SALES_TAX", nullable = false, length = 8)
    private String salesTax;

    @Column(name = "TX_REF_NUM", length = 1)
    private Integer txRefNumId;

    @Column(name = "TX_DATE")
    private LocalDate txDate;

    @Column(name = "TAX")
    private BigDecimal tax;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "PAID", nullable = false)
    private Boolean paid;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "CLIENT_ID", nullable = false)
    private Integer clientId;

}
