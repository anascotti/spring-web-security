package org.springframework.hello;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AppTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/hello").with(testUser())).andExpect(status().isOk());
    }

    @Test
    public void testHelloRedirect() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login").with(testUser())).andExpect(status().isOk());
    }

    public static RequestPostProcessor testUser() {
        return user("user").password("password").roles("USER");
    }
}