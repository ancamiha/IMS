package com.api.sweetshop;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.sweetshop.controller.UserProfileController;
import com.api.sweetshop.model.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserProfileTest {

    @Autowired
    private MockMvc mock;

    @Test
    @Order(1)
    public void registerTest() throws Exception {
        UserProfile userProfile = new UserProfile("Anca", "anca@yahoo.com", "0711111111", "123456");

        mock.perform(post("/SweetShop/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userProfile)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getUsersTest() throws Exception {
        mock.perform(get("/SweetShop/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }

    @Test
    @Order(3)
    public void loginTest() throws Exception {
        UserProfile userProfile = new UserProfile("ancamihaela91@yahoo.com", "123456");

        mock.perform(post("/SweetShop/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userProfile)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void getUserIdCookieTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cookie userIdCookie = new Cookie("userId", "123");
        Cookie[] cookies = {userIdCookie};
        when(request.getCookies()).thenReturn(cookies);

        UserProfileController userProfileController = new UserProfileController();
        long userId = userProfileController.getUserIdCookie(request);

        assertEquals(123, userId);
    }

    @Test
    @Order(5)
    public void checkLoginTest() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userId")).thenReturn(123L);

        UserProfileController userProfileController = new UserProfileController();
        boolean isLoggedIn = userProfileController.checkLogin(session);

        assertTrue(isLoggedIn);
    }

    private String toJson(UserProfile userProfile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userProfile);
    }
}
