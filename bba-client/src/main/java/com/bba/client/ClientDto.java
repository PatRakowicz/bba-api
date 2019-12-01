package com.bba.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Integer id;
    private LocalDateTime created;
    private String status;

    @NotNull
    private String name;
    @NotNull
    private String phone;

    private String lastName;
    private String workPhone;
    private String homePhone;
    private String email;
    private String gender;
    private LocalDate birthdate;
    private String occupation;
    private String family;

    // private String xrefid;
    // private String picUrl;
    // private Integer accountId;
    // private Integer addressId;
}
