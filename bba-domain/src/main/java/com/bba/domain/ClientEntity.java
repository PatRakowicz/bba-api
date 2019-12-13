package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_client")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Integer id;

    @Column(name = "XREFID")
    private String xrefid;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "NAME", nullable = false, length = 64)
    private String name;

    @Column(name = "LNAME", length = 64)
    private String lname;

    @Column(name = "PHONE", nullable = false, length = 16)
    private String phone;

    @Column(name = "WPHONE", length = 16)
    private String wphone;

    @Column(name = "HPHONE", length = 16)
    private String hphone;

    @Column(name = "EMAIL", length = 128)
    private String email;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @Column(name = "BIRTHDATE", length = 10)
    private String birthdate;

    @Column(name = "OCCUPATION", length = 128)
    private String occupation;

    @Column(name = "FAMILY")
    private String family;

    @Column(name = "PIC_URL")
    private String picUrl;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = LocalDateTime.now();
        }
    }
}
