package com.houseofcode.store.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.houseofcode.store.models.PaymentData;
import com.houseofcode.store.models.ShoppingCart;

@RequestMapping("/payment")
@Controller
public class PaymentController {
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Callable<ModelAndView> checkout(RedirectAttributes model) {
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uri, new PaymentData(shoppingCart.getTotal()), String.class);
				System.out.println(response);
				model.addFlashAttribute("success", "Payment was successful");
				return new ModelAndView("redirect:/products");
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				System.out.println(e);
				model.addFlashAttribute("failure", "Value exceeds allowed amount");
				return new ModelAndView("redirect:/products");
			}
		};
	}

}
