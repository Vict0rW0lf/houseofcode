package com.houseofcode.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.houseofcode.store.dao.ProductDAO;
import com.houseofcode.store.models.Product;
import com.houseofcode.store.models.ShoppingCart;
import com.houseofcode.store.models.ShoppingCartItem;
import com.houseofcode.store.models.TypePrice;

@Controller	
@RequestMapping("/cart")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping("/add")
	public ModelAndView add(Long productId, TypePrice typePrice) {
		ModelAndView modelAndView = new ModelAndView("redirect:/products");
		ShoppingCartItem cartItem = createItem(productId, typePrice);
		
		shoppingCart.add(cartItem);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView items() {
		ModelAndView modelAndView = new ModelAndView("cart/items");
		
		return modelAndView;
	}

	private ShoppingCartItem createItem(Long productId, TypePrice typePrice) {
		Product product = productDAO.findBy(productId);
		return new ShoppingCartItem(product, typePrice);
	}
	
	@RequestMapping(value = "/remove/{id}/{typePrice}", method=RequestMethod.POST)
	public ModelAndView remove(Long productId, TypePrice typePrice) {
		shoppingCart.remove(productId, typePrice);
		
		return new ModelAndView("redirect:/cart");
	}
}
