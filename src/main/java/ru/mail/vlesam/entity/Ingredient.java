package ru.mail.vlesam.entity;

import java.util.EnumSet;

/**
 * Created by Vlesam on 24.06.2017.
 */
public enum Ingredient {

    MOZZARELLA,
    PAPRIKA,
    CHICKEN,
    BEEF,
    PORK,
    ONION,
    GARLIC,
    OLIVES,
    PINEAPPLE,
    SHRIMPS,
    SALMON;

    public EnumSet<Ingredient> compatibleWith() {
        EnumSet<Ingredient> exclusions;

        switch (this) {
            case MOZZARELLA:
                exclusions = EnumSet.noneOf(Ingredient.class);
                break;
            case PAPRIKA:
                exclusions = EnumSet.noneOf(Ingredient.class);
                break;
            case CHICKEN:
                exclusions = EnumSet.of(BEEF);
                break;
            case BEEF:
                exclusions = EnumSet.of(CHICKEN);
                break;
            case PORK:
                exclusions = EnumSet.of(ONION, GARLIC, SHRIMPS);
                break;
            case ONION:
                exclusions = EnumSet.of(PORK);
                break;
            case GARLIC:
                exclusions = EnumSet.of(PORK);
                break;
            case OLIVES:
                exclusions = EnumSet.noneOf(Ingredient.class);
                break;
            case PINEAPPLE:
                exclusions = EnumSet.noneOf(Ingredient.class);
                break;
            case SHRIMPS:
                exclusions = EnumSet.of(PORK, SALMON);
                break;
            case SALMON:
                exclusions = EnumSet.of(SHRIMPS);
                break;
            default:
                throw new IllegalArgumentException("Not implemented exclusion list");
        }

        return EnumSet.complementOf(exclusions);
    }

    public boolean compatibleWith(Ingredient ingredient) {
        return compatibleWith().contains(ingredient);
    }


}
