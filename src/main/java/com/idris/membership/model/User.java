package com.idris.membership.model;

import com.idris.membership.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.invoke.VolatileCallSite;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "password")
    private String password;
}
