package ru.mail.vlesam.compatibility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.vlesam.controller.PizzaRequest;
import ru.mail.vlesam.entity.Ingredient;

import java.util.EnumSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Created by Vlesam on 25.06.2017.
 */
@Component
public class PizzaRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PizzaRequest.class.equals(aClass);
    }

    //
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "size", "You should define pizza size [SMALL,MEDIUM, LARGE, EXTRA_LARGE]");
        ValidationUtils.rejectIfEmpty(errors, "ingredients", "You should specify ingredients. See /ingredients");


        PizzaRequest pizzaRequest = (PizzaRequest) o;
        if (!isEmpty(pizzaRequest.getIngredients())) {
            Set<EnumSet<Ingredient>> invalidPairs = validateUniquePairs(pizzaRequest.getIngredients());
            for (EnumSet<Ingredient> invalidPair : invalidPairs) {
                errors.rejectValue("ingredients", "Incompatible ingredient pair: " + invalidPair);
            }
        }
    }

    private Set<EnumSet<Ingredient>> validateUniquePairs(Set<Ingredient> ingredients) {
        Set<EnumSet<Ingredient>> incompatibleIngredients = newHashSet();
        for (Ingredient current : ingredients) {
            for (Ingredient ingredient : ingredients) {
                if (!current.compatibleWith(ingredient)) {
                    incompatibleIngredients.add(EnumSet.of(current, ingredient));
                }
            }
        }
        return incompatibleIngredients;
    }
}
