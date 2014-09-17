package com.pix.controller;

import com.pix.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Andrew on 03.05.14.
 */
@Controller
public class MainController {
    private PixService pix;


    @Autowired
    public MainController(PixService pix){
        this.pix=pix;
    }
    @RequestMapping("/main")
    public String showMainPage(Map<String,Object> model){
        model.put("pics",pix.getRecent(6));
        return "main";
    }
}
