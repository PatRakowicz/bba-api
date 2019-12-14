package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bb_sale_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION", length = 128, nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "TAXABLE", nullable = false)
    private Boolean taxable;

    @Column(name = "SALE_ID", nullable = false)
    private Integer saleId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Integer categoryId;
}
