package com.pix.persistence;
import com.pix.model.Album;
import com.pix.model.Picture;
import com.pix.model.PixUser;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.sql.*;


/**
 * Created by Andrew on 21.08.2014.
 */
public class Insertimage {
    Statement st=null;
    Connection cn=null;
    Picture uploadedPic=new Picture();
    @Autowired
    ServletContext servletContext;

    public Insertimage(){
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
            st = cn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Picture proceed(HttpServletRequest request){
        try
        {
            String ImageFile="";
            String itemName = "";
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart)
            {                                                                               System.out.println("1");
            }
            else
            {                                                                               System.out.println("2");
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try
                {
                    items = upload.parseRequest(request);
                }
                catch (FileUploadException e)
                {
                    e.getMessage();
                }

                Iterator itr = items.iterator();
                while (itr.hasNext())
                {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField())
                    {                                                                       System.out.println("3");
                        String name = item.getFieldName();
                        String value = item.getString();
                        System.out.println(name+ "______________________"+value);
                        Date date=GregorianCalendar.getInstance().getTime();
                        System.out.println(date);
                        uploadedPic.setUploadDate(new Timestamp(date.getTime()));


                        //uploadedPic.setAlbum_id((Integer)request.getAttribute("album_id"));
                        uploadedPic.setUser(((PixUser)request.getSession().getAttribute("user")));
                        if(name.equals("ImageFile")&&value!=null)
                        {
                            ImageFile=value;
                            uploadedPic.setSize(item.getSize());
                        }
                        else if(name.equals("pic_name")&&value!=null){
                            uploadedPic.setName(value);
                        }
                        else if(name.equals("pic_comment")&&value!=null){
                            uploadedPic.setDescription(value);
                        }else if(name.equals("album")&&value!=null){
                            System.out.println("VALUE:"+value);
                            int id_album=Integer.valueOf(value);
                            List<Album> list=(List<Album>)request.getSession().getAttribute("albums");
                            for(Album album:list){
                                if(album.getId()==id_album){
                                    uploadedPic.setAlbum(album);
                                    album.getPictures().add(uploadedPic);
                                }

                            }
                        }
                    }
                    else
                    {
                        try
                        {                                                                    System.out.println("4: "+item.getSize());
                            itemName = item.getName();
                            System.out.println(request.getSession().getServletContext().getRealPath("/resources/Pictures/")+"/"+uploadedPic.getUuid());

                            File savedFile = new File(request.getSession().getServletContext().getRealPath("/resources/Pictures/")+"/"+uploadedPic.getUuid());
                            //System.out.println(servletContext.getRealPath("/")+"Example/image-folder/"+itemName);
                            item.write(savedFile);
                        }
                        catch (Exception e)
                        {System.out.println("404");
                            System.out.println("Error" + e.getMessage());
                        }
                    }
                }
                try
                {                                                                       System.out.println("5");
                    st.executeUpdate("insert into pictures(pic_name) values('"+itemName+"')");

                }
                catch(Exception el)
                {
                   System.out.println("Inserting error"+el.getMessage());
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return uploadedPic;
    }

}
