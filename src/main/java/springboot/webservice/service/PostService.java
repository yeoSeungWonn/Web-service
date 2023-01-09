package springboot.webservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.webservice.domain.post.Post;
import springboot.webservice.domain.post.PostRepository;
import springboot.webservice.dto.PostListResponseDto;
import springboot.webservice.dto.PostResponseDto;
import springboot.webservice.dto.PostSaveRequestDto;
import springboot.webservice.dto.PostUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostResponseDto(post);
    }

    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        postRepository.delete(post);
    }
}
