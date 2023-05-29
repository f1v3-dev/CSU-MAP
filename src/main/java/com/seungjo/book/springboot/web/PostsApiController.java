package com.seungjo.book.springboot.web;


import com.seungjo.book.springboot.service.file.FilesService;
import com.seungjo.book.springboot.service.posts.PostsService;
import com.seungjo.book.springboot.web.dto.PostsResponseDto;
import com.seungjo.book.springboot.web.dto.PostsSaveRequestDto;
import com.seungjo.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@RestController
@Setter
public class PostsApiController {

    private final PostsService postsService;
    private final FilesService filesService;

    @PostMapping("/api/v1/posts")
    public String save(@ModelAttribute PostsSaveRequestDto requestsDto, RedirectAttributes redirectAttributes) throws IOException {
        Long postId = postsService.save(requestsDto);
        filesService.storeFiles(requestsDto.getImageFiles(), postId);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @PutMapping("api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("api/v1/posts/{id}")
    public PostsResponseDto findbyId(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}