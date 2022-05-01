package com.eviescr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CalculateUnitTest {

    private final String ENDPOINT = "/calculation/calculate";

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
    }

    @Test
    public void givenServletContext_whenInitialize_thenContextExists() {
        ServletContext servletContext = applicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(applicationContext.getBean("calculatorController"));
    }

    @Test
    public void shouldReturnCalculateResultDto() throws Exception {
        String params = "?num1=4&num2=5";
        this.mockMvc.perform(get(ENDPOINT + params))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sum").value(9))
                .andExpect(jsonPath("$.multiply").value(20));
    }

    @Test
    public void shouldThrowsMethodArgumentTypeMismatchException() throws Exception {
        String params = "?num1=4&num2=zxc5";
        this.mockMvc.perform(get(ENDPOINT + params))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Failed to convert value of type " +
                        "'java.lang.String' to required type 'java.lang.Double'; nested exception is " +
                        "java.lang.NumberFormatException: For input string: \"zxc5\""));
    }

    @Test
    public void shouldThrowsMissingServletRequestParameterException() throws Exception {
        String params = "?num2=5";
        this.mockMvc.perform(get(ENDPOINT + params))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Required request parameter 'num1' " +
                        "for method parameter type Double is not present"));
    }
}

