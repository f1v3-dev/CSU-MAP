package com.seungjo.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/building")
public class BuildingsController {

    @GetMapping({"/IT","/IT/{floor}"})
    public String ITPage(Model model, @PathVariable(required = false) Integer floor) {
        if(floor != null) {
            model.addAttribute("floor", floor);
        }
        return "/buildings/IT/IT";
    }
}

