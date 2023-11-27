package com.api.sweetshop;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
@TestMethodOrder(OrderAnnotation.class)
public class SweetsControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    @Order(1)
    public void testTestController() throws Exception {
        mock.perform(get("/SweetShop/test"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void addSweetTest() throws Exception {
        String requestBody = "{\"name\":\"Raffaello Cake\",\"description\":\"Almond sponge cake with white chocolate, Mascarpone and coconut filling. \",\"price\":\"28\"}";
        mock.perform(post("/SweetShop/addSweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void deleteSweetTest() throws Exception {
        String requestBody = "{\"name\":\"Raffaello Cake\"}";
        mock.perform(delete("/SweetShop/deleteSweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void getSweetsCountTest() throws Exception {
        mock.perform(get("/SweetShop/sweets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)));
    }
}
