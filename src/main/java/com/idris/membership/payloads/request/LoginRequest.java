package com.idris.membership.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LoginRequest {

    @JsonProperty("email")
    @NotNull(message = "[101] email tidak boleh null")
    @NotEmpty(message = "[102] email tidak boleh kosong")
    @Email(message = "[103] Parameter email tidak sesuai format")
    private String email;

    @JsonProperty("password")
    @NotNull(message = "[101] password tidak boleh null")
    @NotEmpty(message = "[102] password tidak boleh kosong")
    @Length(min = 8, message = "[104] Minimal panjang password adalah 8")
    private String password;

}
