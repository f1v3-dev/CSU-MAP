package com.seungjo.book.springboot.config.auth.dto;

import com.seungjo.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private String uuid;

    public SessionUser(User user) {
        this.uuid = user.getUuid();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
