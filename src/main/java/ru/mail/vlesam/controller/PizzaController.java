package ru.mail.vlesam.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.vlesam.compatibility.PizzaRequestValidator;
import ru.mail.vlesam.service.PizzaBakery;

import javax.validation.Valid;

/**
 * Created by Vlesam on 25.06.2017.
 */
@RestController
public class PizzaController {

    private final PizzaBakery pizzaBakery;
    private final PizzaRequestValidator pizzaRequestValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(pizzaRequestValidator);
    }


    public PizzaController(PizzaBakery pizzaBakery, PizzaRequestValidator pizzaRequestValidator) {
        this.pizzaBakery = pizzaBakery;
        this.pizzaRequestValidator = pizzaRequestValidator;
    }

    @PostMapping(value = "/pizza")
    public PizzaResponse makePizza(@Valid @RequestBody PizzaRequest pizzaRequest) {
        return PizzaResponse.successful(pizzaBakery.bakePizza(pizzaRequest));
    }


}
