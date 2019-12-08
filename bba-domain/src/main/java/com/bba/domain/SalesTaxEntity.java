package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bb_sales_tax")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesTaxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAX_ID")
    private Integer id;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;
}
