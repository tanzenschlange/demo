package ru.mail.vlesam;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import org.junit.Assert;
import org.junit.Test;
import ru.mail.vlesam.entity.Ingredient;

import static org.assertj.core.api.Assertions.*;
import static ru.mail.vlesam.entity.Ingredient.MOZARELLA;
import static ru.mail.vlesam.entity.Ingredient.SALMON;
import static ru.mail.vlesam.entity.Ingredient.SHRIMPS;

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
