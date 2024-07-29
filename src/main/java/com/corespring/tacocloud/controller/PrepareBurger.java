package com.corespring.tacocloud.controller;

import com.corespring.tacocloud.BurgerResources.Burger;
import com.corespring.tacocloud.BurgerResources.Ingredient;
import com.corespring.tacocloud.BurgerResources.OrderDetail;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/prepare")
@SessionAttributes("bookOrder")
public class PrepareBurger {

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        List<Ingredient> ingredients= Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types=Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients,type));
        }

    }

    private Iterable <Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name="bookOrder")
    public OrderDetail orderDetail() {
        return new OrderDetail();
    }

    @ModelAttribute(name="burger")
    public Burger burger() {
        return new Burger();
    }


    @GetMapping
    public String showDesignForm(){
        return "design";
    }
@PostMapping
    public String processBurger(@Valid Burger burger, Errors errors, @ModelAttribute( "bookOrder") OrderDetail orderDetail){
        if (errors.hasErrors()){
            return "design";
        }

        orderDetail.addBurger(burger);
        log.info("processing taco: {}",burger);
        return "redirect:/orders/current";
}


}
