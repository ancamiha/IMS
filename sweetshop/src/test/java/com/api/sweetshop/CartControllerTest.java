package com.api.sweetshop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.sweetshop.pojo.ProdOfCart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CartControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    @Order(1)
    public void addProdToCartTest() throws Exception {
        ProdOfCart prodOfCart = new ProdOfCart(2L, 6L);
        mock.perform(post("/SweetShop/addProdToCart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(prodOfCart)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getCartTest() throws Exception {
        mock.perform(get("/SweetShop/cart/2"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void deleteProdFromCartTest() throws Exception {
        ProdOfCart prodFromCart = new ProdOfCart(2L, 6L);
        mock.perform(delete("/SweetShop/deleteProdFromCart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(prodFromCart)))
                .andExpect(status().isOk());
    }

    private String toJson(ProdOfCart prodOfCart) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(prodOfCart);
    }
}
