package com.bba.client.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Column(name = "LINE2", nullable = true, length = 64)
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
