package com.bba.client.service;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Column(name = "XREFID", nullable = true, length = 255)
	private String xrefid;

	@Column(name = "CREATED", nullable = false)
	private LocalDateTime created;

	@Column(name = "STATUS", nullable = true, length = 1)
	private String status;

	@Column(name = "NAME", nullable = false, length = 64)
	private String name;

	@Column(name = "LNAME", nullable = true, length = 64)
	private String lname;

	@Column(name = "PHONE", nullable = false, length = 16)
	private String phone;

	@Column(name = "WPHONE", nullable = true, length = 16)
	private String wphone;

	@Column(name = "HPHONE", nullable = true, length = 16)
	private String hphone;

	@Column(name = "EMAIL", nullable = true, length = 128)
	private String email;

	@Column(name = "GENDER", nullable = true, length = 1)
	private String gender;

	@Column(name = "BIRTHDATE", nullable = true, length = 10)
	private String birthdate;

	@Column(name = "OCCUPATION", nullable = true, length = 128)
	private String occupation;

	@Column(name = "FAMILY", nullable = true, length = 255)
	private String family;

	@Column(name = "PIC_URL", nullable = true, length = 255)
	private String picUrl;

	@Column(name = "ACCOUNT_ID", nullable = false)
	private Integer accountId;

	@Column(name = "ADDRESS_ID", nullable = true)
	private Integer addressId;

	@PrePersist
	public void prePersist() {
		if (created == null) {
			created = LocalDateTime.now();
		}
	}
}
