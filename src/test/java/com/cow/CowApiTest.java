package com.cow;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.cow.models.Cow;
import com.cow.repositories.CowRepository;
import com.cow.services.CowService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class CowApiTest extends AbstractIntegrationTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private CowRepository cowRepository;
    
    @Autowired
    private CowService cowService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        cowRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testAddCow() throws Exception {

        Cow cow = new Cow("1","2261");
        
        ResultActions response = mockMvc.perform(post("/cows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cow)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.collarId").value(cow.getCollarId()))
                .andExpect(jsonPath("$.cowNumber").value(cow.getCowNumber()))
                .andExpect(jsonPath("$.collarStatus").value("Healthy"));

    }
    
    
    @Test
    @Order(2)
    public void testGetCows() throws Exception{
        List<Cow> cows = new ArrayList<>();
        cows.add(new Cow("1","2261"));
        cows.add(new Cow("2", "2263"));
        cowRepository.saveAll(cows);
        ResultActions response = mockMvc.perform(get("/cows"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(cows.size()));

    }
    
    
    @Test
    @Order(3)
    public void testGetCowById() throws Exception{
        Cow cow = new Cow("1", "2261");
    	cowService.add(cow);

        ResultActions response = mockMvc.perform(get("/cows/getCow/{id}", cow.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.collarId").value(cow.getCollarId()))
                .andExpect(jsonPath("$.cowNumber").value(cow.getCowNumber()))
                .andExpect(jsonPath("$.collarStatus").value("Healthy"));

    }
    
    
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception{
        Cow cow = new Cow("1","2261");
        cowRepository.save(cow);
        Cow cow2 = new Cow("2","5561");

        ResultActions response = mockMvc.perform(put("/cows/{id}", cow.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cow2)));

        response.andExpect(status().isOk())
                .andDo(print()).
                andExpect(jsonPath("$.collarId").value(cow2.getCollarId()))
                .andExpect(jsonPath("$.cowNumber").value(cow2.getCowNumber()))
                .andExpect(jsonPath("$.collarStatus").value("Broken"));
    }
    
}
