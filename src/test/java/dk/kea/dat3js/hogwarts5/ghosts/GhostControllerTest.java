package dk.kea.dat3js.hogwarts5.ghosts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GhostController.class)
class GhostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllGhosts() throws Exception {
        mockMvc.perform(get("/ghosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder(
                        "Nearly Headless Nick",
                        "The Bloody Baron",
                        "The Fat Friar",
                        "The Grey Lady"
                )));
    }

    @Test
    void getGhostByName() throws Exception {
        mockMvc.perform(get("/ghosts/Nearly Headless Nick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$.realName").value("Sir Nicholas de Mimsy-Porpington"))
                .andExpect(jsonPath("$.house").value("Gryffindor"));
    }
}