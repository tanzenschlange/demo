package ru.mail.vlesam.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.vlesam.entity.Ingredient;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Vlesam on 25.06.2017.
 */
@RestController
public class IngredientController {

    @RequestMapping("/ingredients")
    public Set<Ingredient> ingredients() {
        return EnumSet.allOf(Ingredient.class);
    }

    @RequestMapping("/ingredients/allCompatibleWith/{ingredient}")
    public Set<Ingredient> ingredients(@PathVariable Ingredient ingredient) {
        return ingredient.compatibleWith();
    }

    @RequestMapping("/ingredients/{ingredient}/compatibleWith/{with}")
    public boolean ingredients(@PathVariable Ingredient ingredient, @PathVariable Ingredient with) {
        return ingredient.compatibleWith(with);
    }


}
