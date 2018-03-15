package org.springframework.hello.api.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hello.WebSecurityConfig;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {WebSecurityConfig.class, HelloController.class})
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGreeting() throws Exception {
        
        MvcResult loginResponse = mockMvc.perform(post("/hello/api/login")
             .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
             .content("{\"username\":\"ana\", \"password\":\"password\"}"))
            .andExpect(status().isOk()).andReturn();
        
        String authHeader = loginResponse.getResponse().getHeader("Authorization").replaceAll("Bearer", "").trim();
        System.out.println(authHeader);
        
        mockMvc.perform(get("/hello/api/v1/greeting")
                .header("Authorization", authHeader))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testGreeting401() throws Exception {
        mockMvc.perform(get("/hello/api/v1/greeting"))
            .andExpect(status().is(401));
    }
    
}
