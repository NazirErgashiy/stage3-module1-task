package com.mjc.school.repository.persistance.entity;

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
