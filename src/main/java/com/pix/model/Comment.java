package com.pix.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrew on 21.04.14.
 */
public class Comment implements Serializable{
    private Integer id;
    private String comment;
    private Date date= new Date();

    public Comment() {
    }

    public Comment(Integer id,String comment) {
        this.id=id;
        this.comment = comment;
    }
}
