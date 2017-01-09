package com.onlineocr.nick.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by GrIfOn on 08.01.2017.
 */
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "c_id")
    @NotNull
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @NotNull
    @Column(name = "c_value")
    private String value;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
