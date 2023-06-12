package com.seungjo.book.springboot.web;

import com.seungjo.book.springboot.config.auth.LoginUser;
import com.seungjo.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/buildings")
public class BuildingsController {

    @GetMapping({"/IT", "/IT/{floor}"})
    public String ITPage(@LoginUser SessionUser user, Model model, @PathVariable(required = false) Integer floor) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }
        if(floor != null) {
            model.addAttribute("floor", floor);
        }
        return "/buildings/IT/IT";
    }
    
}

