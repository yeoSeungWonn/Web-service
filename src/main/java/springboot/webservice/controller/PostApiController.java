package springboot.webservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.webservice.dto.PostResponseDto;
import springboot.webservice.dto.PostSaveRequestDto;
import springboot.webservice.dto.PostUpdateRequestDto;
import springboot.webservice.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(
            @PathVariable("id") Long id,
            @RequestBody PostUpdateRequestDto requestDto
    ) {
        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable("id") Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return id;
    }
}
