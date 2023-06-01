package com.seungjo.book.springboot.web;

import com.seungjo.book.springboot.service.file.FilesService;
import com.seungjo.book.springboot.service.posts_noticeService.Posts_noticeService;
import com.seungjo.book.springboot.web.dto.noticeDto.Posts_noticeResponseDto;
import com.seungjo.book.springboot.web.dto.noticeDto.Posts_noticeSaveRequestDto;
import com.seungjo.book.springboot.web.dto.noticeDto.Posts_noticeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@Setter
public class Posts_noticeApiController {
    private final Posts_noticeService posts_noticeService;
    private final FilesService filesService;

    @PostMapping("/api/v2/posts")
    public String save(@ModelAttribute Posts_noticeSaveRequestDto requestsDto, RedirectAttributes redirectAttributes) throws IOException {
        Long postId = posts_noticeService.save(requestsDto);
        filesService.storeFiles(requestsDto.getImageFiles(), postId);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @PutMapping("api/v2/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody Posts_noticeUpdateRequestDto requestDto) {
        return posts_noticeService.update(id, requestDto);
    }

    @GetMapping("api/v2/posts/{id}")
    public Posts_noticeResponseDto findbyId(@PathVariable Long id) {
        return posts_noticeService.findById(id);
    }

    @DeleteMapping("api/v2/posts/{id}")
    public Long delete(@PathVariable Long id){
        posts_noticeService.delete(id);
        return id;
    }
}
