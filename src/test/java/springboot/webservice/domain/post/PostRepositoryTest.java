package springboot.webservice.domain.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach // 단위 테스트가 끝날때 마다 수행되는 메서드, 데이터간 침범 막기
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "title";
        String content = "content";

        postRepository.save(Post.builder()
                        .title(title)
                        .author("ysw")
                        .content(content)
                .build());

        //when
        List<Post> all = postRepository.findAll();

        //then
        Post post = all.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntity_등록() {
        //given
        LocalDateTime of = LocalDateTime.of(2022, 1, 9, 0, 0, 0);
        postRepository.save(Post.builder()
                        .title("title")
                        .author("author")
                        .content("content")
                .build());

        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post post = postList.get(0);

        System.out.println(post.getCreatedDate().toString() + post.getModifiedDate().toString());
        assertThat(post.getCreatedDate().isAfter(of));
        assertThat(post.getModifiedDate().isAfter(of));
    }

}