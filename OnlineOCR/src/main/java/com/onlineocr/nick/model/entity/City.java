package com.onlineocr.nick.model.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by GrIfOn on 08.01.2017.
 */
@Entity
@Table(name = "city")
public class City {
    @Id
    @NotNull
    @Column(name = "ct_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(strategy = "increment", name = "increment")
    private Long id;

    @NotNull
    @Column(name = "ct_value")
    private String value;

    public City() {
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
