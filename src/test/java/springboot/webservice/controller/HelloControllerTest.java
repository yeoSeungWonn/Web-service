package springboot.webservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) // 스프링부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class) // web(spring mvc)에만 집중할 수 있는 어노테이션, @Controller, @ControllerAdvice등 사용가능
class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API 테스트시 사용

    @Test
    public void hello가_리턴된다() throws Exception {
        String str = "hello";

        mvc.perform(get("/hello")) // mockmvc를 통해 /hello 주소로 get 요청을 한다.
                .andExpect(status().isOk()) // mvc.perform의 결과 검증, header의 status 검증
                .andExpect(content().string(str)); // 응답 본문의 내용 검증(여기서는 str의 내용과 일치하는지 검증)
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "name";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(r ->
                {jsonPath("$.name", is(name));
                })
                .andExpect(r -> {jsonPath("$.amount", is(amount));});
    }
    //이제는 andExpect가  ResultMatcher만을 파라미터로 받는다. 

}