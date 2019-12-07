package com.bba.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bb_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Integer id;

    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "TYPE", length = 1)
    private String type;

    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @Column(name = "PARENT_ID", nullable = false)
    private Integer parentId;
}
