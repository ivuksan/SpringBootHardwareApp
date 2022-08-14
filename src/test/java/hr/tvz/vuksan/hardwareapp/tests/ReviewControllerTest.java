package hr.tvz.vuksan.hardwareapp.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    private final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDk0OTI2NSwiaWF0IjoxNjU0MzQ0NDY1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ._Z7xElwLGCvXNLxasqulRQ957Y643KjalOLblCggeTkgt_xOWzbRUg_LC1hdySXCI-BYlw9kED0R9gCQYGo-Kw";

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("deprecation")
    @Test
    void getAllReviews() throws Exception{
        this.mockMvc.perform(
                get("/review")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @SuppressWarnings("deprecation")
    @Test
    void getReviewsByCode() throws Exception{
        long TEST_CODE = 1;
        this.mockMvc.perform(
                get("/review/" + TEST_CODE)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}