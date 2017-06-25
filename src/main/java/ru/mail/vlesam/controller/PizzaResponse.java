package ru.mail.vlesam.controller;

import ru.mail.vlesam.entity.Pizza;

import java.time.LocalDateTime;

/**
 * Created by Vlesam on 24.06.2017.
 */
public class PizzaResponse {

    private final Pizza pizza;
    private final LocalDateTime bakedAt;
    private final String exception;

    private PizzaResponse(Pizza pizza, String exception) {
        this.pizza = pizza;
        this.exception = exception;
        this.bakedAt = LocalDateTime.now();
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

    public LocalDateTime getBakedAt() {
        return bakedAt;
    }

    public String getException() {
        return exception;
    }
}
