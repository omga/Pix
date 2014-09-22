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
    public List<Album> getAlbums(PixUser user) {
        Query query=currentSession().createQuery("from Album where user=:user");
        query.setEntity("user", user);
        List<Album> albums= query.list();
        System.out.println(albums);
        return albums;
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void updateAlbum(Album album) {
        currentSession().update(album);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addPicture(Picture pic) {
        currentSession().save(pic);
        //currentSession().getTransaction().commit();
//        try{
//            currentSession().merge(pic);
//            currentSession().flush();
//            currentSession().close();}
//        catch (JDBCConnectionException se){se.printStackTrace();}
//        catch (ConstraintViolationException ce){ce.printStackTrace();}
//        catch (Exception e){e.printStackTrace();}

    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public List<Picture> getUserPictures(PixUser user){
        List<Picture> pictures;
        Query query=currentSession().createQuery("from picture where user=:user");
        query.setEntity("user", user);
        pictures=query.list();
        return pictures;
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public List<Picture> getAlbumPictures(Album album){
        List<Picture> pictures;
        Query query=currentSession().createQuery("from picture where album=:album");
        query.setEntity("album", album);
        pictures=query.list();
        return pictures;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public List<Picture> getRecentPictures(){
        List<Picture> pictures;
        Query query=currentSession().createQuery("from picture order by id desc");
        query.setMaxResults(3);
        pictures=query.list();
        return pictures;
    }
    @Override
    public void addTest(TestEntity obj){
        currentSession().save(obj);
    }
}
