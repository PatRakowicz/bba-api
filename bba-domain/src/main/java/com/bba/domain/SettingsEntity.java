package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bb_settings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "KEY_", nullable = false, length = 64)
    private String key;

    @Column(name = "VAL_", nullable = false)
    private String val;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;
}
