package ru.mail.vlesam.controller;

import ru.mail.vlesam.entity.Pizza;

/**
 * Created by Vlesam on 24.06.2017.
 */
public class PizzaResponse {

    private final Pizza pizza;
    private final String exception;

    private PizzaResponse(Pizza pizza, String exception) {
        this.pizza = pizza;
        this.exception = exception;
    }

    public static PizzaResponse successful(Pizza pizza) {
        return new PizzaResponse(pizza, null);
    }

    public static PizzaResponse failed(Exception exception) {
        return new PizzaResponse(null, exception.toString());
    }


    public Pizza getPizza() {
        return pizza;
    }


    public String getException() {
        return exception;
    }
}
