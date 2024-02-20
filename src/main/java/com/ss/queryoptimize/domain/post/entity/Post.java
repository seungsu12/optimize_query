package com.ss.queryoptimize.domain.post.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Post {
    private final Long id;
    private final Long memberId;
    private final String content;
    private final LocalDate createDate;
    private final LocalDateTime createdAt;

    @Builder
    public Post(Long id, Long memberId, String content, LocalDate createDate, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.content = Objects.requireNonNull(content);
        this.createDate = createDate ;
        this.createdAt = createdAt  == null ? LocalDateTime.now() : createdAt;
    }
}
