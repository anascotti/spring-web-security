package org.springframework.hello.api.v1;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {ApiSecurityConfig.class, HelloController.class})
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGreeting() throws Exception {
        mockMvc.perform(get("/hello/api/v1/greeting").with(apiUser()))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testGreeting401() throws Exception {
        mockMvc.perform(get("/hello/api/v1/greeting"))
            .andExpect(status().is(401));
    }
    
    public static RequestPostProcessor apiUser() {
        return user("user").password("password").roles("USER");
    }
}
