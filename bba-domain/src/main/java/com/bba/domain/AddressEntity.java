package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bb_address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer id;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "LINE1", nullable = false, length = 128)
    private String line1;

    @Column(name = "LINE2", length = 64)
    private String line2;

    @Column(name = "CITY", nullable = false, length = 64)
    private String city;

    @Column(name = "STATE", nullable = false, length = 2)
    private String state;

    @Column(name = "ZIP", nullable = false, length = 16)
    private String zip;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;
}
