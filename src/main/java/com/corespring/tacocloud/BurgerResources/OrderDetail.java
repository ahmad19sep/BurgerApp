package com.corespring.tacocloud.BurgerResources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDetail {
    @NotBlank
    @Pattern(regexp = "([a-zA-z])",message = "Only in  alphabetic characters ")
    private String deliveryName;
    @NotBlank
    @Pattern(regexp = "(0-9 && a-zA-z)",message = "Format: 123 Main Street ")
    private String deliveryStreet;
    @NotBlank
    @Pattern(regexp = "([a-zA-z])",message = "Only in  alphabetic characters ")
    private String deliveryCity;
    @NotBlank
    @Pattern(regexp = "([a-zA-z])",message = "Only in  alphabetic characters ")
    private String deliveryState;
    @NotBlank
    @Pattern(regexp = "(^[0-9]{5}$)",message = "5 digit zip code ")
    private String deliveryZip;
    @NotBlank
    @CreditCardNumber(message = "not a valid credit card Number")
    private String ccNumber;
    @NotBlank
    @Pattern(regexp = "[0-9]{2}/[0-9]{2}",message = "MM/YY")
    private String ccExpiryDate;
    @NotBlank
    @Pattern(regexp = "[0-9]{3}")
    private String ccCVV;

    private List<Burger> burgers =new ArrayList<>();

    public void addBurger(Burger burger){
        burgers.add(burger);
    }
}
