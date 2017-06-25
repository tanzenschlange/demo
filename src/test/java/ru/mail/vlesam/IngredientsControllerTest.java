package ru.mail.vlesam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.mail.vlesam.controller.IngredientController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Vlesam on 25.06.2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IngredientController.class)
public class IngredientsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllIngredients() throws Exception {
        this.mockMvc.perform(get("/ingredients")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[\"MOZARELLA\",\"PAPRIKA\",\"CHICKEN\",\"BEEF\",\"PORK\",\"ONION\",\"GARLIC\",\"OLIVES\",\"PINEAPPLE\",\"SHRIMPS\",\"SALMON\"]"));
    }

    @Test
    public void getAllCompatibleWithPork() throws Exception {
        this.mockMvc.perform(get("/ingredients/allCompatibleWith/PORK")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[\"MOZARELLA\",\"PAPRIKA\",\"CHICKEN\",\"BEEF\",\"PORK\",\"OLIVES\",\"PINEAPPLE\",\"SALMON\"]"));
    }


    @Test
    public void salmonIsCompatibleWithMozarella() throws Exception {
        this.mockMvc.perform(get("/ingredients/SALMON/compatibleWith/MOZARELLA")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void porkIsNotCompatibleWithShrimps() throws Exception {
        this.mockMvc.perform(get("/ingredients/PORK/compatibleWith/SHRIMPS")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("false"));
    }


    @Test
    public void unknownIngredientsProduceException() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/ingredients/BEER/compatibleWith/VODKA")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(mvcResult.getResolvedException().getClass()).isAssignableFrom(MethodArgumentTypeMismatchException.class);
    }

}
