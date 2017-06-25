package ru.mail.vlesam.controller;

import ru.mail.vlesam.entity.Ingredient;
import ru.mail.vlesam.entity.Size;

import java.util.Set;

/**
 * Created by Vlesam on 24.06.2017.
 */
public class PizzaRequest {

    private Size size;
    private Set<Ingredient> ingredients;

    public void setSize(Size size) {
        this.size = size;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Size getSize() {
        return size;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
}
