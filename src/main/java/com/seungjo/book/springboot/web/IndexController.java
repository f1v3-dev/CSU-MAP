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
import com.seungjo.book.springboot.web.dto.fileDto.FilesListResponseDto;
import com.seungjo.book.springboot.web.dto.findDto.FindDto;
import com.seungjo.book.springboot.web.dto.noticeDto.Posts_noticeResponseDto;
import com.seungjo.book.springboot.web.dto.postDto.PostsListResponseDto;
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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class IndexController {


    private final PostsService postsService;
    private final Posts_noticeService posts_noticeService;

    private final UserRepository userRepository;
    private final FilesService filesService;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }


    //@LoginUser를 사용하여 세션 정보를 가져옴
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }
        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("uuidValue", user.getUuid());
        }
        return "post/posts-save";
    }

    @GetMapping("/posts_notice/save")
    public String posts_noticeSave(Model model, @LoginUser SessionUser user) {
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

        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())) {
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
        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())) {
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
        if (filename.equals("NoImage")) {
            return new UrlResource("file:" + filesService.NoImage());
        }
        return new UrlResource("file:" + filesService.getFullPath(filename));
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        PostsResponseDto dto = postsService.findById(id);
        List<Files> filesList = filesService.findByPostId(id);

        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())) {
            model.addAttribute("equalUuid", user.getUuid());
        }
        model.addAttribute("post", dto);
        model.addAttribute("filesList", filesList);

        return "post/posts-update";
    }

    @GetMapping("/posts_notice/update/{id}")
    public String posts_noticeUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        Posts_noticeResponseDto dto = posts_noticeService.findById(id);
        System.out.println("dto.getUuid() = " + dto.getUuid());
        System.out.println("user.getUuid() = " + user.getUuid());

        if (dto.getUuid() != null && user.getUuid() != null && dto.getUuid().equals(user.getUuid())) {
            model.addAttribute("equalUuid", user.getUuid());
        }
        model.addAttribute("post", dto);

        return "post_notice/posts_notice-update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model) {
        List<Posts> searchList = postsService.search(keyword);

        model.addAttribute("searchList", searchList);

        return "post/posts-search";
    }

    @GetMapping("/posts_notice/search")
    public String noticesearch(String keyword, Model model) {
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
    public String noticePage(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "0") int page) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());

            Optional<User> userRole = userRepository.findByEmail(user.getEmail());
            if (userRole.get().getRole() == Role.ADMIN) {
                model.addAttribute("write", userRole.get().getRole());
            }
        }
        int size = 10;
        int page_size = 10;
        Page<Posts_notice> resultList = posts_noticeService.getPostList(page, size);
        if (page > resultList.getTotalPages())
            page = resultList.getTotalPages();
        List<PageList> arr = new ArrayList<>();
        int start = (page/page_size)*page_size;
        int end = resultList.getTotalPages() < start+10 ? resultList.getTotalPages() : start+10;
        for (int i=start; i<end; i++) {
            arr.add(new PageList(i, i+1));
        }
        model.addAttribute("resultList", resultList);
        model.addAttribute("arr", arr);

        if (start != 0) {
            int prev = start-10;
            model.addAttribute("hasPrev", true);
            model.addAttribute("prev", prev);
        }
        else {
            model.addAttribute("hasPrev", false);
        }
        if (start+10 < resultList.getTotalPages()) {
            int next = start+10;
            model.addAttribute("hasNext", true);
            model.addAttribute("next", next);
        }
        else {
            model.addAttribute("hasNext", false);
        }
        return "nav/notice";

    }

    @GetMapping("/find")
    public String findPage(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        List<PostsListResponseDto> posts = postsService.findAllDesc();
        List<FilesListResponseDto> firstImg = filesService.findFirstImg();

        List<FindDto> combinedList = new ArrayList<>();
        Map<Long, FilesListResponseDto> imgMap = firstImg.stream()
                .collect(Collectors.toMap(FilesListResponseDto::getPostId, Function.identity()));

        for (PostsListResponseDto post : posts) {
            FilesListResponseDto img = imgMap.getOrDefault(post.getId(), null);

            FindDto findDto = FindDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .author(post.getAuthor())
                    .content(post.getContent())
                    .originalFileName(img != null ? img.getOriginalFileName() : "NoImage")
                    .savedFileName(img != null ? img.getSavedFileName() : "NoImage")
                    .modifiedDate(post.getModifiedDate())
                    .build();

            combinedList.add(findDto);
        }


        model.addAttribute("posts", combinedList);


        return "nav/find";
    }
}