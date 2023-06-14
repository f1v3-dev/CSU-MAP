package com.seungjo.book.springboot.web;

import com.seungjo.book.springboot.config.auth.LoginUser;
import com.seungjo.book.springboot.config.auth.dto.SessionUser;
import com.seungjo.book.springboot.domain.it.Classrooms;
import com.seungjo.book.springboot.domain.it.Lectures;
import com.seungjo.book.springboot.service.IT.ITService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/buildings")
public class BuildingsController {

    private final ITService itService;

    @GetMapping({"/IT", "/IT/{floor}"})
    public String ITPage(@LoginUser SessionUser user, Model model, @PathVariable(required = false) Integer floor) {

        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        List<Classrooms> classrooms = itService.findClassrooms(floor);
        List<ClassroomList> classroomList = new ArrayList<>();

        for (Classrooms classroom : classrooms) {
            classroomList.add(new ClassroomList(classroom.getId(), classroom.getName()));
        }

        model.addAttribute("classroomList", classroomList);

        if (floor != null && floor >= 1 && floor <= 10) {
            model.addAttribute("floor", floor);
        } else {
            model.addAttribute("floor", 1);
            return "redirect:/buildings/IT/1";
        }

        return "buildings/IT/IT";
    }


}

