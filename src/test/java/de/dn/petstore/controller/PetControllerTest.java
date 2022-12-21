package de.dn.petstore.controller;

import de.dn.petstore.api.controller.PetController;
import de.dn.petstore.repository.PetRepository;
import de.dn.petstore.service.PetService;
import de.dn.petstore.utils.mapper.PetMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {
    @MockBean
    private PetRepository petRepository;

    @MockBean
    private PetService petService;

    @MockBean
    private PetMapper petMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void listAllPets_validParams_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/pets")
                        .param("limit", "100")
                        .param("offset", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void listAllPets_exceedingLimit_internalError() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/pets")
                        .param("limit", "101")
                        .param("offset", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void createPet_validContent_ok() throws Exception {
        String json = """
                {
                  "name": "test",
                  "tag": "testtag"
                }
                """;

        mvc.perform(MockMvcRequestBuilders
                        .post("/pets")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createPet_invalidMediaType_unsupportedMediaType() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/pets")
                        .content("")
                        .contentType(MediaType.TEXT_PLAIN)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void createPet_invalidContent_badRequest() throws Exception {
        String json = """
                {
                   "name": null
                }
                 """;

        mvc.perform(MockMvcRequestBuilders
                        .post("/pets")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void showPetById_notExistingId_notFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/pets/{petId}", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updatePetById_invalidContent_badRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/pets/{petId}", "2")
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePetById_notExistingId_notFound() throws Exception {
        String json = """
                {
                   "name": "testpet"
                }
                 """;

        mvc.perform(MockMvcRequestBuilders
                        .put("/pets/{petId}", "2")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
