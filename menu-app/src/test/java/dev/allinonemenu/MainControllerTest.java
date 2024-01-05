package dev.allinonemenu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHttp200Response() throws Exception {
        // GETリクエストを送信し、HTTP 200が返されることを確認
        mockMvc.perform(get("/")) // 実際のエンドポイントを指定
                .andExpect(status().isOk());
    }
}
