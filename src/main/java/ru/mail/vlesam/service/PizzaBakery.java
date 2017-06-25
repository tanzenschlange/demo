package ru.mail.vlesam.service;

import org.springframework.stereotype.Service;
import ru.mail.vlesam.controller.PizzaRequest;
import ru.mail.vlesam.entity.Pizza;

/**
 * Created by Vlesam on 25.06.2017.
 */
@Service
public class PizzaBakery {

    public Pizza bakePizza(PizzaRequest pizzaRequest) {
        return new Pizza(pizzaRequest.getSize(), pizzaRequest.getIngredients());
    }

}
