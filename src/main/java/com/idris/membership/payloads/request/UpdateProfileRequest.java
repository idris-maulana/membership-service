package com.idris.membership.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UpdateProfileRequest {
    @JsonProperty("first_name")
    @NotNull(message = "[101] first_name tidak boleh null")
    @NotEmpty(message = "[102] first_name tidak boleh kosong")
    private String firstName;

    @JsonProperty("last_name")
    @NotNull(message = "[101] last_name tidak boleh null")
    @NotEmpty(message = "[102] last_name tidak boleh kosong")
    private String lastName;
}
