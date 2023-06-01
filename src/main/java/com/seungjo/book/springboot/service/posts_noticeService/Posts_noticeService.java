package com.seungjo.book.springboot.service.posts_noticeService;

import com.seungjo.book.springboot.domain.posts_notice.Posts_notice;
import com.seungjo.book.springboot.domain.posts_notice.Posts_noticeRepository;
import com.seungjo.book.springboot.service.file.FilesService;
import com.seungjo.book.springboot.web.dto.noticeDto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class Posts_noticeService {
    private final Posts_noticeRepository posts_noticeRepository;
    private final FilesService filesService;


    @Transactional
    public Long save(Posts_noticeSaveRequestDto requestDto) {
        return posts_noticeRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public Long update(Long id, Posts_noticeUpdateRequestDto requestDto) {
        Posts_notice posts_notice = posts_noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts_notice.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public Posts_noticeResponseDto findById(Long id) {
        Posts_notice entity = posts_noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new Posts_noticeResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<Posts_noticeListResponseDto> findAllDesc() {
        return posts_noticeRepository.findAllDesc().stream()
                .map(Posts_noticeListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts_notice posts_notice = posts_noticeRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts_noticeRepository.delete(posts_notice);
    }

    @Transactional
    public List<Posts_notice> search(String keyword) {
        return posts_noticeRepository.findByTitleContaining(keyword);
    }
}
