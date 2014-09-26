package com.pix.controller;

import com.pix.model.Album;
import com.pix.model.Picture;
import com.pix.model.PixUser;
import com.pix.service.MyAuthUser;
import com.pix.service.PixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


@Controller
@RequestMapping(value = "/")
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private PixService pixService;

    @RequestMapping(method = RequestMethod.GET)
    public String showHomepage(Model model, HttpSession session, Authentication auth, Principal principal) {
        if (auth != null) {
            try {
                User user = (User) ((Authentication) principal).getPrincipal();
                PixUser pu = ((MyAuthUser) user).getPixUser();
                session.setAttribute("user", pu);
                session.setAttribute("username", pu.getUserName());
                session.setAttribute("albums", pu.getAlbums());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Picture pic1=new Picture("1.jpg");
        pic1.setName("Jessica1");
        Picture pic2=new Picture("4.png");
        Picture pic3=new Picture("6.jpg");
        pic3.setName("yura");
        List<Picture> pics=new ArrayList<Picture>();
        pics.add(pic1);pics.add(pic2);pics.add(pic3);
        model.addAttribute("pics",pics);
        logger.debug("Temperature set to {}. Old temperature was {}.", 5, 50);
        //System.out.println(pixService.getAlbums().get(0));
        System.out.println(session.getAttribute("username"));
        return "home";
    }

    //USER LOGIN
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String signIn(Model model,HttpSession session,HttpServletRequest request){
        String username=request.getParameter("j_username");
        String pass=request.getParameter("j_password");
        logger.info("parameters: 1= "+username+" 2= "+pass);
        PixUser pu=pixService.login(username,pass);
        if(pu==null){
            model.addAttribute("message","wrong password or username, try again");
            showHomepage(model, session, null, null);
            return "home";
        }

        session.setAttribute("user",pu);
        session.setAttribute("username",username);

        //List<Album> albums=new ArrayList<Album>();
        //        albums= pixService.getAlbums(pu);
        // session.setAttribute("albums",albums);
        return "home";

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showSignInForm(Model model){
        model.addAttribute("pixUser", new PixUser());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute("pixUser") PixUser user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println("wrong data");
            return "registration";
        }
        System.out.println(user);
        System.out.println(user.getId());
        pixService.addUser(user);
        Album album=new Album("main");
        album.setUser(user);
        pixService.createAlbum(album);
        return "home";//+user.getUserName();
    }

    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET)
    public String showUserPage(@PathVariable String username,Model model,HttpSession session){
        PixUser user=(PixUser)session.getAttribute("user");
        //List<Album> albums=pixService.getAlbums(user);
        //model.addAttribute("user",pixService.getUser(username));
        //model.addAttribute("albums",albums);
        List<Picture> pictures=pixService.getUserPictures(user);
        // pictures.get(0).setAlbum(albums.get(0));
        //System.out.println(pictures.get(0)+" album: "+albums.get(0)+" user: "+user);
        // System.out.println(pictures.get(0).getId()+" ____ "+pictures.get(0).getAlbum());

        model.addAttribute("pics",pictures);
        return "profile";
    }
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showUserTestPage(@PathVariable String username,Model model,HttpSession session){
        PixUser user=(PixUser)session.getAttribute("user");
//        List<Album> albums=pixService.getAlbums(user);
//        model.addAttribute("albums",albums);
        return "profile2";
    }

    @RequestMapping(value = "/users/insertimage",method = RequestMethod.POST)
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        pixService.uploadPic(request);
        return "redirect:/";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(Model model){

        return "upload";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadp(HttpServletRequest request, HttpServletResponse response) {
        pixService.uploadPic(request);
        return "redirect:/";
    }
    //
    @RequestMapping(value = "/picture-ajax",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Picture> getAlbumPics(@RequestParam("id") String id,HttpSession session){//@PathVariable String name,@PathVariable String description){
        //String var=req.getParameter("id");
        System.out.println("________________________________________"+id);
        List<Picture> pictures=null;
        int album_id=Integer.valueOf(id);
        List<Album> list=(List<Album>)session.getAttribute("albums");
        for(Album album:list){
            if(album.getId()==album_id)
                pictures=pixService.getAlbumPictures(album);
        }
        System.out.println(pictures.get(0));
        return pictures;
    }

    @RequestMapping(value = "/album-ajax",method = RequestMethod.POST)
    @ResponseBody
    public String addAlbum(@RequestParam String name,@RequestParam String description,HttpSession session){
        String res="success";
        System.out.println(name+description+" _ "+session.getAttribute("username"));

        Album album=new Album(name);
        album.setDescription(description);
        album.setCreationDate(new Timestamp(GregorianCalendar.getInstance().getTime().getTime()));
        try {
            album.setUser(((PixUser) session.getAttribute("user")));
            pixService.createAlbum(album);
        }catch (Exception e){res="FAILUREEEEEEEEEEEEE";}
        List<Album> albums=pixService.getAlbums((PixUser)session.getAttribute("user"));
        session.setAttribute("albums",albums);
        return res;
    }


}