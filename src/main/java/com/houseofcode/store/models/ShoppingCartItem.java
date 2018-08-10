package com.houseofcode.store.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Product product;
	private TypePrice typePrice;

	public ShoppingCartItem(Product product, TypePrice typePrice) {
		this.product = product;
		this.typePrice = typePrice;
	}
	
	public BigDecimal getPrice() {
		return product.priceTo(typePrice);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public TypePrice getTypePrice() {
		return typePrice;
	}

	public void setTypePrice(TypePrice typePrice) {
		this.typePrice = typePrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((typePrice == null) ? 0 : typePrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartItem other = (ShoppingCartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (typePrice != other.typePrice)
			return false;
		return true;
	}

	public BigDecimal getTotal(int quantity) {
		return this.getPrice().multiply(new BigDecimal(quantity));
	}
	
}
