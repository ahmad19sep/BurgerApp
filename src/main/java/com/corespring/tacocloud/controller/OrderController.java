package com.corespring.tacocloud.controller;

import com.corespring.tacocloud.BurgerResources.OrderDetail;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("bookOrder")
public class OrderController {
    @GetMapping("/current")
    public String orders(Model model) {
        // Check if bookOrder is in the session
        if (!model.containsAttribute("bookOrder")) {
            log.error("bookOrder not found in session!");
        } else {
            log.info("bookOrder found in session: {}", model.getAttribute("bookOrder"));
        }
        return "ordersForm";
    }

    @PostMapping
    public String processOrder(@Valid OrderDetail orderDetail, Errors errors, SessionStatus sessionStatus){

        if (errors.hasErrors()){
            return "ordersForm";
        }

        log.info("Order Submitted: {}", orderDetail);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
