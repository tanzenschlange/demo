package ru.mail.vlesam;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mail.vlesam.entity.Ingredient.*;

/**
 * Created by Vlesam on 24.06.2017.
 */
public class IngredientCompatibilityTest {

    @Test
    public void testCompatibility(){
        assertThat(SALMON.compatibleWith())
                .contains(MOZARELLA)
                .doesNotContain(SHRIMPS);
    }

}
