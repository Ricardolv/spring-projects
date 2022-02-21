package com.richard.tamingthymeleaf.infrastructure.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootResource {

    @GetMapping
    public String root() {
        return "redirect:/users";
    }

}
