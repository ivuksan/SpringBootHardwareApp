package hr.tvz.vuksan.hardwareapp.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.vuksan.hardwareapp.Type;
import hr.tvz.vuksan.hardwareapp.hardware.HardwareCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    private final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDk0OTI2NSwiaWF0IjoxNjU0MzQ0NDY1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ._Z7xElwLGCvXNLxasqulRQ957Y643KjalOLblCggeTkgt_xOWzbRUg_LC1hdySXCI-BYlw9kED0R9gCQYGo-Kw";
    private final String userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU0OTYwNjk3LCJpYXQiOjE2NTQzNTU4OTcsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIn0.ivnBb7f0xAAXOrs8XqEM7G-4xHxFPb4VG9gNmutb0dj6R46aamjbsn7X-7VCPbMTdcSdSG6TLnW3nBOP0IfZGA";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("deprecation")
    @Test
    void getAllHardware() throws Exception{
        this.mockMvc.perform(
                get("/hardware")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @SuppressWarnings("deprecation")
    @Test
    void getHardwareByCode() throws Exception{
        long TEST_CODE = 1;
        this.mockMvc.perform(
                get("/hardware/" + TEST_CODE)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @SuppressWarnings("deprecation")
    @Test
    void saveHardwareAsAdmin() throws Exception{
        long TEST_CODE = 7;
        String TEST_NAME = "MSI B450";
        double TEST_PRICE = 140.0;
        Type TEST_TYPE = Type.MBO;
        int TEST_AMOUNT = 15;

        HardwareCommand hardwareCommand = new HardwareCommand(
                TEST_NAME, TEST_CODE, TEST_PRICE, TEST_TYPE, TEST_AMOUNT
        );

        this.mockMvc.perform(
                post("/hardware")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(hardwareCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @SuppressWarnings("deprecation")
    @Test
    void saveHardwareAsUser() throws Exception{
        long TEST_CODE = 6;
        String TEST_NAME = "MSI RTX 3080";
        double TEST_PRICE = 999.0;
        Type TEST_TYPE = Type.GPU;
        int TEST_AMOUNT = 2;

        HardwareCommand hardwareCommand = new HardwareCommand(
                TEST_NAME, TEST_CODE, TEST_PRICE, TEST_TYPE, TEST_AMOUNT
        );

        this.mockMvc.perform(
                        post("/hardware")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }
}