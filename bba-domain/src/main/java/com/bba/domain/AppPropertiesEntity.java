package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "app_properties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppPropertiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "APP_CODE", length = 8)
    private String appCode;

    @Column(name = "GROUP_", length = 64)
    private String group;

    @Column(name = "KEY_")
    private String key;

    @Column(name = "VALUE_")
    private String value;

}
