package ru.mail.vlesam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vlesam on 25.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void emptyPizzaRequestProduceExceptions() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/pizza")
                .content("{}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest()).andReturn();
        MethodArgumentNotValidException e = (MethodArgumentNotValidException) result.getResolvedException();

        assertThat(e.getBindingResult().getFieldError("ingredients").getCode()).isEqualTo("You should specify ingredients. See /ingredients");
        assertThat(e.getBindingResult().getFieldError("size").getCode()).isEqualTo("You should define pizza size [SMALL,MEDIUM, LARGE, EXTRA_LARGE]");
        assertThat(e.getBindingResult().getAllErrors())
                .hasSize(2);

    }



    @Test
    public void requestWithIncompatibleComponentsProduceException() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/pizza")
                .content("{\"size\":\"LARGE\", \"ingredients\":[\"SHRIMPS\", \"SALMON\", \"BEEF\", \"PORK\"]}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
        MethodArgumentNotValidException e = (MethodArgumentNotValidException) result.getResolvedException();

        List<FieldError> errors = e.getBindingResult().getFieldErrors("ingredients");
        assertThat(errors).hasSize(2);
        assertThat(errors.get(0).getCode()).isEqualTo("Incompatible ingredient pair: [PORK, SHRIMPS]");
        assertThat(errors.get(1).getCode()).isEqualTo("Incompatible ingredient pair: [SHRIMPS, SALMON]");
    }


    @Test
    public void pizzaBaked() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/pizza")
                .content("{\"size\":\"LARGE\", \"ingredients\":[\"SHRIMPS\", \"MOZARELLA\", \"OLIVES\"]}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString())
                .contains("{\"pizza\":{\"size\":\"LARGE\",\"ingredients\":[\"SHRIMPS\",\"MOZARELLA\",\"OLIVES\"]}");

    }
}
