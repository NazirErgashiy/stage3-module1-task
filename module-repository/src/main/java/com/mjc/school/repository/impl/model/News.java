package com.mjc.school.repository.impl.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class News {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private long authorId;
}
