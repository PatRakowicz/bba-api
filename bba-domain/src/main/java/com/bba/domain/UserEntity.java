package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bb_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Column(name = "ACTIVE", length = 1)
    private String active;

    @Column(name = "LOGIN_NAME", length = 128)
    private String loginName;

    @Column(name = "LOGIN_PASS", length = 128)
    private String loginPass;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column(name = "LAST_IP", nullable = false, length = 128)
    private String lastIP;

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = LocalDateTime.now();
        }
    }
}
