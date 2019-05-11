package com.garage48.kigaliair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
public class KigaliairController {
    private String appMode;

    @Autowired
    public KigaliairController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("projectName", "KigaliAir");
        model.addAttribute("mode", appMode);

        return "index";
    }
}
