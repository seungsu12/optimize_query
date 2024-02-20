package com.ss.queryoptimize.domain.post.service;

import com.ss.queryoptimize.domain.post.dto.DailyPostCount;
import com.ss.queryoptimize.domain.post.dto.DailyPostCountRequest;
import com.ss.queryoptimize.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request){
        /*
            반환 값 -> 리스트 [작성일자, 작성회원, 작성 게시물 갯수]

            select * from Post
             where memberId =:memberId and createDate between firstDate and lastDate
             group by createDate memberId
         */
        return postRepository.groupByCreatedDate(request);
    }
}
