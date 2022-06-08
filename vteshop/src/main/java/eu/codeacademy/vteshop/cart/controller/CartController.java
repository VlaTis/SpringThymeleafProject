package eu.codeacademy.vteshop.cart.controller;

import eu.codeacademy.vteshop.cart.dto.CartDto;
import eu.codeacademy.vteshop.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@SessionAttributes("cartSession")
@RequiredArgsConstructor
public class CartController {

    private static final String CART_ROOT_PATH = "/cart";
//    public static final String PUBLIC_CART_ROOT_PATH = PUBLIC_WORKSPACE + CART_ROOT_PATH;

    private final CartService cartService;

    @ModelAttribute("cartSession")
    public CartDto createCart() {
        return new CartDto();
    }

    @GetMapping(CART_ROOT_PATH )
    public String openCart(@ModelAttribute("cartSession") CartDto cart) {
        return "/sales/cart";
    }

    @PostMapping(CART_ROOT_PATH  + "/add")
    public String addToCart(@ModelAttribute("cartSession") CartDto cart, @RequestParam UUID productId) {
        cartService.addToCartByProductId(productId, cart);

        return "redirect:" + "/products";
    }

    @PostMapping(CART_ROOT_PATH + "/order")
    public String order(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        //TODO: save into DB or do another things with cart data

        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("successMessage", "cart.order.message.success");

        return "redirect:" + "/products";
    }
}