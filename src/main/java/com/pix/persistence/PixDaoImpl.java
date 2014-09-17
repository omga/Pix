package com.pix.persistence;

import com.pix.model.Album;
import com.pix.model.Picture;
import com.pix.model.PixUser;
import com.pix.model.TestEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrew on 28.08.2014.
 */
@Service("PixDaoImpl")
@Repository
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PixDaoImpl implements PixDao {
    private SessionFactory sessionFactory;

    @Autowired
    public PixDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;

    }
    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(PixUser user) {

        try{
            currentSession().save(user);
        }catch (Exception e){
            e.printStackTrace(); return;
        }
        Album album=new Album("main");
        album.setUser(user.getId());
        addAlbum(album);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void editUser(PixUser user) {
        currentSession().update(user);
    }

    public PixUser getUser(String username){
        return new PixUser();
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public PixUser loginUser(String username, String pass){
        PixUser pixuser;
        Query query=currentSession().createQuery("from PixUser WHERE userName=:username");
        query.setString("username",username);
        try{
            pixuser=(PixUser)query.list().get(0);
            if(pixuser.getPassword().equals(pass))
                return pixuser;
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong username.");
        }catch (Exception e){
            System.out.println("Wrong password.");
        }
        return null;
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addAlbum(Album album) {
        currentSession().save(album);
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public List<Album> getAlbums(int user_id) {
        Query query=currentSession().createQuery("from Album where user_id=:user_id");
        query.setInteger("user_id", user_id);
        List<Album> albums= query.list();
        System.out.println(albums);
        return albums;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addPicture(Picture pic) {
        try{currentSession().save(pic);}
        catch (JDBCConnectionException se){se.printStackTrace();}
        catch (ConstraintViolationException ce){ce.printStackTrace();}
        catch (Exception e){e.printStackTrace();}

    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public List<Picture> getUserPictures(int user_id){
        List<Picture> pictures;
        Query query=currentSession().createQuery("from Picture where user_id=:user_id");
        query.setInteger("user_id", user_id);
        pictures=query.list();
        return pictures;
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public List<Picture> getAlbumPictures(int album_id){
        List<Picture> pictures;
        Query query=currentSession().createQuery("from Picture where album_id=:album_id");
        query.setInteger("album_id", album_id);
        pictures=query.list();
        return pictures;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public List<Picture> getRecentPictures(){
        List<Picture> pictures;
        Query query=currentSession().createQuery("from Picture order by id desc");
        query.setMaxResults(3);
        pictures=query.list();
        return pictures;
    }
    @Override
    public void addTest(TestEntity obj){
        currentSession().save(obj);
    }
}
