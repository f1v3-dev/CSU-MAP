package com.seungjo.book.springboot.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageList {
    int var1;
    int var2;

    public PageList(int var1, int var2) {
        this.var1 = var1;
        this.var2 = var2;

    }
}
