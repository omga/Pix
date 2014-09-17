package com.pix.service;

import com.pix.model.Album;
import com.pix.model.Picture;
import com.pix.model.PixUser;
import com.pix.model.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.pix.persistence.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 03.05.14.
 */
@Service("PixService")
public class PixService {
    PixDao pixDao;
    @Autowired
    PixService(PixDao pixDaoImpl){
        this.pixDao=pixDaoImpl;
    }


    private List<Picture> allPics=new ArrayList<Picture>();

    public List<Picture> getAllPics() {
        return allPics;
    }

    public void setAllPics(List<Picture> allPics) {
        this.allPics = allPics;
    }

    public List<Picture> getRecent(int num){
        int size=allPics.size();
        return (size>num) ? allPics.subList(size-num,size) : allPics;
    }
    public List<Picture> getAlbumPictures(int album_id){
        return pixDao.getAlbumPictures(album_id);
    }

    public void addUser(PixUser user){
        System.out.println(".........trying to save the user");
        pixDao.addUser(user);
        System.out.println("USER SAVED!");
    }

    public PixUser getUser(String username){
        PixUser pu= new PixUser();//ssssssssssssssssssssss
        pu.setUserName(username);
        return pu;
    }

    public PixUser login(String username, String password){
        PixUser pu=pixDao.loginUser(username,password);
        return pu;
    }

    public void uploadPic(HttpServletRequest request){
        Insertimage ii=new Insertimage();
        Picture picture=ii.proceed(request);
        pixDao.addPicture(picture);
    }

    public void addTest(TestEntity tst){
        pixDao.addTest(tst);
    }

    public List<Album> getAlbums(int user_id){
        return pixDao.getAlbums(user_id);
    }

    public void createAlbum(Album album){
        pixDao.addAlbum(album);
    }
    public List<Picture> getUserPictures(int user_id){
        return pixDao.getUserPictures(user_id);
    }


}