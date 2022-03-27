package com.lshhi5.betplan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public String Hello(Model model) {
        model.addAttribute("name", "world");
        return "hello";
    }
}
