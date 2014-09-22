package com.pix.persistence;

import com.pix.model.*;

import java.util.List;

/**
 * Created by Andrew on 28.08.2014.
 */
public interface PixDao {
    void addUser(PixUser user);
    void editUser(PixUser user);
    PixUser getUser(String username);
    PixUser loginUser(String username, String pass);
    void addAlbum(Album album);
    List<Album> getAlbums(PixUser user);
    void updateAlbum(Album album);
    void addPicture(Picture pic);
    List<Picture> getUserPictures(PixUser user);
    List<Picture> getAlbumPictures(Album album);
    List<Picture> getRecentPictures();


    void addTest(TestEntity obj);


}
