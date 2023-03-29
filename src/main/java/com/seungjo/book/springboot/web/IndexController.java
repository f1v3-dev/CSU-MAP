package com.seungjo.book.springboot.web;

import com.seungjo.book.springboot.config.auth.LoginUser;
import com.seungjo.book.springboot.config.auth.dto.SessionUser;
import com.seungjo.book.springboot.service.posts.PostsService;
import com.seungjo.book.springboot.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    
    //@LoginUser를 사용하여 세션 정보를 가져옴
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}