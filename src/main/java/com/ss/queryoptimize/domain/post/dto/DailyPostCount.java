package com.ss.queryoptimize.domain.post.dto;

import java.time.LocalDate;

public record DailyPostCount (Long memeberId, LocalDate date, Long postCount){
}
