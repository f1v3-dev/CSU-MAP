package com.seungjo.book.springboot.web;

import com.seungjo.book.springboot.config.auth.LoginUser;
import com.seungjo.book.springboot.config.auth.dto.SessionUser;
import com.seungjo.book.springboot.domain.file.Files;
import com.seungjo.book.springboot.domain.posts.Posts;
import com.seungjo.book.springboot.domain.posts.PostsRepository;
import com.seungjo.book.springboot.domain.user.Role;
import com.seungjo.book.springboot.domain.user.User;
import com.seungjo.book.springboot.domain.user.UserRepository;
import com.seungjo.book.springboot.service.file.FilesService;
import com.seungjo.book.springboot.service.posts.PostsService;
import com.seungjo.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class IndexController {


    private final PostsService postsService;

    private final UserRepository userRepository;
    private final FilesService filesService;
    private final PostsRepository postsRepository;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }


    //@LoginUser를 사용하여 세션 정보를 가져옴
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }
        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("uuidValue", user.getUuid());
        }
        return "post/posts-save";
    }

    @GetMapping("posts/{id}")
    public String posts(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        PostsResponseDto dto = postsService.findById(id);
        List<Files> filesList = filesService.findByPostId(id);
        for (Files files : filesList) {
            System.out.println("files.getSavedFileName() = " + files.getSavedFileName());
        }
        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())){
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("equalUuid", user.getUuid());
        }
        model.addAttribute("post", dto);
        model.addAttribute("filesList", filesList);

        return "post/posts-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + filesService.getFullPath(filename));
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        PostsResponseDto dto = postsService.findById(id);
        System.out.println("dto.getUuid() = " + dto.getUuid());
        System.out.println("user.getUuid() = " + user.getUuid());

        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())){
            model.addAttribute("equalUuid", user.getUuid());
        }
        model.addAttribute("post", dto);

        return "post/posts-update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model){
        List<Posts> searchList = postsService.search(keyword);

        model.addAttribute("searchList", searchList);

        return "post/posts-search";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @LoginUser SessionUser user) throws Exception {
        if (isAuthenticated()) {
            if (user != null) {
                model.addAttribute("loginUserName", user.getName());
            }
            return "index";
        }
        return "oauth/login";
    }

    @GetMapping("/introduce")
    public String introducePage(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }
        return "nav/introduce";
    }

    @GetMapping("/notice")
    public String noticePage(Model model, @LoginUser SessionUser user){

        if (user != null) {
            model.addAttribute("loginUserName", user.getName());

            Optional<User> userRole = userRepository.findByEmail(user.getEmail());
            if (userRole.get().getRole() == Role.ADMIN) {
                model.addAttribute("write", userRole.get().getRole());
            }
        }
        model.addAttribute("posts", postsService.findAllDesc());
        return "nav/notice";
    }

    @GetMapping("/find")
    public String findPage(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        return "nav/find";
    }

    @GetMapping({"/IT", "/IT/{floor}"})
    public String ITPage(@PathVariable(required = false) Integer floor) {
        if (floor != null) {
            return "IT/IT_" + floor;
        } else {
            return "IT/IT";
        }
    }
}