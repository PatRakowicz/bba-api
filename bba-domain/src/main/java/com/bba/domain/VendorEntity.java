package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bb_vendor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_ID")
    private Integer id;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "BUSINESS_NAME", nullable = false, length = 64)
    private String businessName;

    @Column(name = "BUSINESS_PHONE", length = 16)
    private String businessPhone;

    @Column(name = "TAX_ID", length = 64)
    private String taxId;

    @Column(name = "REP_NAME", length = 128)
    private String repName;

    @Column(name = "REP_PHONE", length = 16)
    private String repPhone;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "ADDRESS_ID")
    private Integer addressId;
}
