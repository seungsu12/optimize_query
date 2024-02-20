package com.ss.queryoptimize.application.controller;

import com.ss.queryoptimize.domain.post.dto.DailyPostCount;
import com.ss.queryoptimize.domain.post.dto.DailyPostCountRequest;
import com.ss.queryoptimize.domain.post.dto.PostCommand;
import com.ss.queryoptimize.domain.post.service.PostReadService;
import com.ss.queryoptimize.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostWriteService postWriteService;
    private final PostReadService postReadService;

    @PostMapping("/posts")
    public Long create(PostCommand command){
        return postWriteService.create(command);
    }

    @GetMapping("/daily-post-count")
    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request){
        return postReadService.getDailyPostCount(request);
    }
}
