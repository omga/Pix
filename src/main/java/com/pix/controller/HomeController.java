package com.pix.controller;

import com.pix.model.Album;
import com.pix.model.Picture;
import com.pix.model.PixUser;
import com.pix.service.PixService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@Controller
@RequestMapping(value = "/")
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private PixService pixService;

    @RequestMapping(method = RequestMethod.GET)
    public String showHomepage(Model model, HttpSession session){

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
    @RequestMapping(method = RequestMethod.POST)
    public String signIn(Model model,HttpSession session,HttpServletRequest request){
        String username=request.getParameter("j_username");
        String pass=request.getParameter("j_password");

        logger.info("parameters: 1= "+username+" 2= "+pass);
        PixUser pu=pixService.login(username,pass);
        if(pu==null){
            model.addAttribute("message","wrong password or username, try again");
            showHomepage(model,session);
            return "home";
        }
        session.setAttribute("user",pu);
        session.setAttribute("username",username);
        List<Album> albums=new ArrayList<Album>();
                albums= pixService.getAlbums(pu.getId());
        session.setAttribute("albums",albums);
        System.out.println(albums.get(0).getName());
        showHomepage(model, session);
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
        System.out.println(user);
        return "redirect:/users/"+user.getUserName();
    }

    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET)
    public String showUserPage(@PathVariable String username,Model model,HttpSession session){
        int user_id=((PixUser)session.getAttribute("user")).getId();
        List<Album> albums=pixService.getAlbums(user_id);
        //model.addAttribute("user",pixService.getUser(username));
        model.addAttribute("albums",albums);
        List<Picture> pictures=pixService.getUserPictures(user_id);
        model.addAttribute("pics",pictures);
        return "profile";
    }
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showUserTestPage(@PathVariable String username,Model model,HttpSession session){
        int user_id=((PixUser)session.getAttribute("user")).getId();
        List<Album> albums=pixService.getAlbums(user_id);
        model.addAttribute("albums",albums);
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

    @RequestMapping(value = "/picture-ajax",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Picture> getAlbumPics(@RequestParam("id") String id){//@PathVariable String name,@PathVariable String description){
        //String var=req.getParameter("id");
        System.out.println("________________________________________"+id);
        int album_id=Integer.valueOf(id);
        List<Picture> pictures=pixService.getAlbumPictures(album_id);
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
            album.setUser(((PixUser) session.getAttribute("user")).getId());
            pixService.createAlbum(album);
        }catch (Exception e){res="FAILUREEEEEEEEEEEEE";}
        return res;
    }


}