package com.pix.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="pixuser")
public class PixUser implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pixuser_id_user_seq")
    @SequenceGenerator(name="pixuser_id_user_seq", sequenceName="pixuser_id_user_seq", allocationSize=1)
    @Column(name="id_user")
    private int id;
    @Column(name="username", length = 20)
    @Size(min=3,max=20,message = "username must be between 3 and 20 characters")
    @Pattern(regexp = "[a-zA-Z0-9]+$",message = "username must be alpanumeric with no spaces")
    private String userName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;
    @Column(name="password",length = 20)
    @Size(min=6,max=20,message = "password must be betweend 6 and 20 characters long.")
    private String password;
    @Transient
    private List<Comment> comments;
    @Transient
    private List<Album> albums;

    public PixUser(){

    }

    public PixUser(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

//    @Transient
//    public void addAlbum(Album album){
//        album.setUser(this);
//        albums.add(album);
//    }
//    @Transient
//    public void addComment(Comment comment){
//        comments.add(comment);
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PixUser)) return false;

        PixUser pixUser = (PixUser) o;

        if (!email.equals(pixUser.email)) return false;
        if (!password.equals(pixUser.password)) return false;
        if (userName != null ? !userName.equals(pixUser.userName) : pixUser.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
    @Override
    public String toString(){
        return "user: "+userName+", "+firstName+" "+lastName+", "+email;
    }
}
