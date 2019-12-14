package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bb_purchase_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false, length = 128)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "PURCHASE_ID", nullable = false)
    private Integer purchaseId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Integer categoryId;
}
