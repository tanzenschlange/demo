package ru.mail.vlesam.entity;

import java.util.Set;

/**
 * Created by Vlesam on 25.06.2017.
 */
public class Pizza {

    private final Size size;
    private final Set<Ingredient> ingredients;

    public Pizza(Size size, Set<Ingredient> ingredients) {
        this.size = size;
        this.ingredients = ingredients;
    }

    public Size getSize() {
        return size;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
}
