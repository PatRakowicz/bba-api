package com.bba.client.service;

import java.time.LocalDateTime;

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
@Table(name = "bb_client_note")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientNoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CREATED", nullable = false, length = 19)
	private LocalDateTime created;

	@Column(name = "NOTE", nullable = false, length = 255)
	private String note;

	@Column(name = "CLIENT_ID", nullable = false)
	private Integer clientId;
}
