package com.seungjo.book.springboot.web;

import com.seungjo.book.springboot.config.auth.LoginUser;
import com.seungjo.book.springboot.config.auth.dto.SessionUser;
import com.seungjo.book.springboot.domain.file.Files;
import com.seungjo.book.springboot.domain.posts.Posts;
import com.seungjo.book.springboot.domain.posts.PostsRepository;
import com.seungjo.book.springboot.domain.posts_notice.Posts_notice;
import com.seungjo.book.springboot.domain.user.Role;
import com.seungjo.book.springboot.domain.user.User;
import com.seungjo.book.springboot.domain.user.UserRepository;
import com.seungjo.book.springboot.service.file.FilesService;
import com.seungjo.book.springboot.service.posts.PostsService;
import com.seungjo.book.springboot.service.posts_noticeService.Posts_noticeService;
import com.seungjo.book.springboot.web.dto.FileDto.FilesListResponseDto;
import com.seungjo.book.springboot.web.dto.noticeDto.Posts_noticeResponseDto;
import com.seungjo.book.springboot.web.dto.postDto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class IndexController {


    private final PostsService postsService;
    private final Posts_noticeService posts_noticeService;

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

    @GetMapping("/posts_notice/save")
    public String posts_noticeSave(Model model, @LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("uuidValue", user.getUuid());
        }
        return "post_notice/posts_notice-save";
    }

    @GetMapping("/posts/{id}")
    public String posts(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        PostsResponseDto dto = postsService.findById(id);
        List<Files> filesList = filesService.findByPostId(id);

        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())){
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("equalUuid", user.getUuid());
        }
        model.addAttribute("post", dto);
        model.addAttribute("filesList", filesList);

        return "post/posts-view";
    }
    @GetMapping("/posts_notice/{id}")
    public String posts_notice(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        Posts_noticeResponseDto dto = posts_noticeService.findById(id);
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

        return "post_notice/posts_notice-view";
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
        List<Files> filesList = filesService.findByPostId(id);

        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())){
            model.addAttribute("equalUuid", user.getUuid());
        }
        model.addAttribute("post", dto);
        model.addAttribute("filesList", filesList);

        return "post/posts-update";
    }

    @GetMapping("/posts_notice/update/{id}")
    public String posts_noticeUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        Posts_noticeResponseDto dto = posts_noticeService.findById(id);
        System.out.println("dto.getUuid() = " + dto.getUuid());
        System.out.println("user.getUuid() = " + user.getUuid());

        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())){
            model.addAttribute("equalUuid", user.getUuid());
        }
        model.addAttribute("post", dto);

        return "post_notice/posts_notice-update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model){
        List<Posts> searchList = postsService.search(keyword);

        model.addAttribute("searchList", searchList);

        return "post/posts-search";
    }

    @GetMapping("/posts_notice/search")
    public String noticesearch(String keyword, Model model){
        List<Posts_notice> searchList = posts_noticeService.search(keyword);

        model.addAttribute("searchList", searchList);

        return "post_notice/posts_notice-search";
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
    public String noticePage(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "0") int page){
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());

            Optional<User> userRole = userRepository.findByEmail(user.getEmail());
            if (userRole.get().getRole() == Role.ADMIN) {
                model.addAttribute("write", userRole.get().getRole());
            }
        }
        int size = 3;
        Page<Posts_notice> resultList = posts_noticeService.getPostList(page, size);
        List<PageList> arr = new ArrayList<>();
        for (int i=0; i<resultList.getTotalPages(); i++) {
            arr.add(new PageList(i, i+1));
        }
        model.addAttribute("resultList", resultList);
        model.addAttribute("arr", arr);

        return "nav/notice";
    }

    @GetMapping("/find")
    public String findPage(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        Page<Posts> list = postsService.pageList(pageable);
        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "nav/find";
    }
}