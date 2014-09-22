package com.pix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * Created by Andrew on 21.04.14.
 */
@Entity(name = "picture")
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="picture_id_seq")
    @SequenceGenerator(name="picture_id_seq", sequenceName="picture_id_seq", allocationSize=1)
    private Integer id;
    @Column(name = "pic_uuid")
    private String uuid;
    private String name;
    private String description;
    private long size;
    @Column(name = "date")
    private Date uploadDate;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="ALBUM_ID",nullable = false)
    private Album album;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="USER_ID",nullable = false)
    private PixUser user;

    @Column(name = "is_private")
    private boolean isPrivate=false;

    public Picture() {
        this.uuid=UUID.randomUUID().toString();
    }

    public Picture( String uuid){

        this.uuid=uuid;
    }
    public Picture(String name, String description){
        this.name=name;
        this.description=description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUuid(){
        return uuid;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public PixUser getUser() {
        return user;
    }

    public void setUser(PixUser user) {
        this.user = user;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @Override
    public String toString() {
        return "picture: "+getName()+" uuid: "+getUuid()+" size: "+getSize()+" ALB AND USER:"+ user+" "+album;
    }
}
