package springboot.webservice.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloResponseDtoTest {

    @Test
    public void 롬복_테스트() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount());

    }

}