package com.corespring.tacocloud.BurgerResources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Burger {
    @NotNull
    @Size(min=5,max=25,message = "Enter a valid Name")
    private String name;
    @NotNull
    @Size(min=1,message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
