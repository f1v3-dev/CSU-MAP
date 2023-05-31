package com.seungjo.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/building")
public class BuildingsController {

    @GetMapping({"/IT"})
    public String ITPage(@PathVariable(required = false) Integer floor) {
        if (floor != null) {
            return "IT/IT_" + floor;
        } else {
            return "buildings/IT/IT";
        }
    }
}

