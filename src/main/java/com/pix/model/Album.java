package com.pix.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 21.04.14.
 */
@Entity
@Table(name = "album")
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="album_id_seq")
    @SequenceGenerator(name="album_id_seq", sequenceName="album_id_seq", allocationSize=1)
    private Integer id;
    private int user_id;
    @Column(name = "album_name")
    private String name;
    private String description;
    @Column(name = "creation_date")
    private Date creationDate = new Date();
    @Transient
    private String[] labels;
    @Transient
    private List<Picture> pictures = new ArrayList<Picture>();

    public Album() {
    }

    public Album(String name) {
        this.name = name;
    }

    public void addPicture(Picture picture){
        picture.setAlbum_id(this.id);
        pictures.add(picture);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUser() {
        return user_id;
    }

    public void setUser(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
    public String toString(){
        return "Album "+name+", description:"+description;
    }
}
