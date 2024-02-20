package com.ss.queryoptimize.domain.post.service;

import com.ss.queryoptimize.domain.post.dto.PostCommand;
import com.ss.queryoptimize.domain.post.entity.Post;
import com.ss.queryoptimize.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteService {

    private final PostRepository postRepository;

    public Long create(PostCommand command){
        var post = Post
                .builder()
                .memberId(command.memberId())
                .content(command.contents())
                .build();
        return postRepository.save(post).getId();
    }
}
