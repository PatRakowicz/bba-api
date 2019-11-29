package com.bba.contacts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDto {

	private String name;
	private String phone;
	private String email;
}
