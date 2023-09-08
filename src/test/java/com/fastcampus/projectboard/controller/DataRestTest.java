package com.fastcampus.projectboard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// @WebMvcTest 어노테이션으로 엔드포인트 테스트를 했더니 실패했다. 왜냐하면 WebMvcTest가 슬라이스 테스트이기 때문에 컨트롤러 외의 빈들을 로드하지 않는다.
// 즉, DataRest의 autoConfiguration을 읽지 않는 것이다.
@DisplayName("Data REST - API 테스트")
@Transactional // integration test라서 api를 실행한 결과가 repository까지 실행을 시켜 hibernate 쿼리까지 실행되는걸 볼 수 있는데 즉,
// DB에 영향을 주는 것이기 때문에 @Transactional 을 붙였다. 이로써 테스트에서의 기본동작은 롤백이 된다. 즉, DataRestTest밑에 각각의 unit test
// 들은 롤백 상태로 트랜잭션이 묶이게 된다.
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }


}
