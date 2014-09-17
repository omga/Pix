package com.pix.model;

import javax.persistence.*;

/**
 * Created by Andrew on 07.09.2014.
 */
@Entity
public class TestEntity {
    @Id
    private int Id;
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
