package com.ss.queryoptimize.domain.post.repository;

import com.ss.queryoptimize.domain.post.dto.DailyPostCount;
import com.ss.queryoptimize.domain.post.dto.DailyPostCountRequest;
import com.ss.queryoptimize.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    static final String TABLE = "post";
    private static final RowMapper<DailyPostCount> DAILY_POST_COUNT_ROW_MAPPER = (ResultSet resultSet, int roNum)
    -> new DailyPostCount(resultSet.getLong("memberId"),
            resultSet.getObject("createdDate",LocalDate.class)
            ,resultSet.getLong("count"));
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Post save(Post post) {
        if (post.getId() == null)
            return insert(post);
        throw new UnsupportedOperationException("Post는 갱신을 지원하지 않습니다.");
    }

    private Post insert(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate());

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(post);
        var id = jdbcInsert.executeAndReturnKey(params).longValue();
        return Post.builder()
                .id(id)
                .memberId(post.getMemberId())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request) {
        var sql = """
                  select * from Post
                             where memberId =:memberId and createDate between firstDate and lastDate
                             group by createDate , memberId
                """.formatted();

        var params = new BeanPropertySqlParameterSource(request);


        return namedParameterJdbcTemplate.query(sql,params,DAILY_POST_COUNT_ROW_MAPPER);
    }
}
