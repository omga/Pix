package com.pix.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * Created by Andrew on 21.04.14.
 */
@Entity
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="picture_pic_id_seq")
    @SequenceGenerator(name="picture_pic_id_seq", sequenceName="picture_pic_id_seq", allocationSize=1)
    @Column(name="pic_id")
    private Integer id;
    @Column(name = "pic_uuid")
    private String uuid;
    private String name;
    private String description;
    private long size;
    @Column(name = "date")
    private Date uploadDate;
    private int album_id;
    private int user_id;
    @Transient
    private Set<Comment> comments = new HashSet<Comment>();
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


    public void addComment(Comment comment){
        comments.add(comment);
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
}
