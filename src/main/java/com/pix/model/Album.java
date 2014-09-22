package com.pix.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrew on 21.04.14.
 */
@Entity
@Table(name = "album")
public class Album implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="album_id_seq",
            sequenceName="album_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="album_id_seq")
    private Integer id;
    @Column(name = "album_name")
    private String name;
    private String description;
    @Column(name = "creation_date")
    private Date creationDate = new Date();
    @ManyToOne()
    @JoinColumn(name="USER_ID",nullable = false)
    private PixUser user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "album",fetch = FetchType.EAGER)
    private List<Picture> pictures;

    public Album() {
    }

    public Album(String name) {
        this.name = name;
    }

    public void addPicture(Picture picture){
        picture.setAlbum(this);
        pictures.add(picture);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PixUser getUser() {
        return user;
    }

    public void setUser(PixUser user) {
        this.user = user;
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
