package com.koziolpaulina.foodmanager.recipes;

import javax.persistence.Embeddable;

@Embeddable
public class Ingredient {

    private String name;

    private Integer quantity;

    public Ingredient() {
    }

    public Ingredient(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (!name.equals(that.name)) return false;
        return quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name + " " + String.valueOf(quantity);
    }
}
