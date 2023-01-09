package springboot.webservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springboot.webservice.dto.PostResponseDto;
import springboot.webservice.service.PostService;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;

    /**
     * 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정, 뷰 리졸버가 처리
     * */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String savePost() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
