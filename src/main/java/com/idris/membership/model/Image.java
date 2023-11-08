package com.idris.membership.model;

import com.idris.membership.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "images")
public class Image extends BaseEntity {

    @Column(name = "filename", unique = true)
    private String filename;

    @Lob
    @Column(name = "raw")
    private String raw;
}
