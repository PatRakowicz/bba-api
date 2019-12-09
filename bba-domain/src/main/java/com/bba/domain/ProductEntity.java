package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Integer id;

    @Column(name = "UPDATED", nullable = false)
    private LocalDateTime updated;

    @Column(name = "ACTIVE", nullable = false, length = 1)
    private String active;

    @Column(name = "DESCRIPTION", nullable = false, length = 128)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TAXABLE", nullable = false)
    private Boolean taxable;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Integer categoryId;
}
