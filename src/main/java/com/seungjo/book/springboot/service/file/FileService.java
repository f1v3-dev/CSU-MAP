package com.seungjo.book.springboot.service.file;

import com.seungjo.book.springboot.domain.file.FileRepository;
import com.seungjo.book.springboot.domain.file.UploadFile;
import com.seungjo.book.springboot.domain.posts.Posts;
import com.seungjo.book.springboot.web.dto.FileDto;
import com.seungjo.book.springboot.web.dto.PostsResponseDto;
import com.seungjo.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    @Transactional
    public Long saveDb(FileDto requestDto) {
        return fileRepository.save(requestDto.toEntity()).getId();
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        // FileDto 객체 생성
        FileDto fileDto = FileDto.builder()
                .originalFileName(originalFilename)
                .savedFileName(storeFileName)
                .size(multipartFile.getSize())
                .build();

        // File Insert
        Long fileId = saveDb(fileDto);


        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public FileDto findById(Long id) {
        com.seungjo.book.springboot.domain.file.File entity = fileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 파일이 없습니다. id=" + id));

        return new FileDto(entity);
    }


}

