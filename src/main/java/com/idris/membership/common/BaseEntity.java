package com.idris.membership.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "version")
    private Long version;

    @PrePersist
    void onSave() {
        setVersion(1L);
    }

    @PreUpdate
    void onUpdate() {
        setVersion(getVersion() + 1);
    }

 }
