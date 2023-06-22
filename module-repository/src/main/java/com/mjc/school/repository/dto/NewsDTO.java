package com.mjc.school.repository.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDTO {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private long authorId;
}
