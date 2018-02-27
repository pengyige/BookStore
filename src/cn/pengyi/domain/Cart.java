package cn.pengyi.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<String,CartItem> cart = new HashMap();
	private double price;
	
	public Map<String, CartItem> getCart() {
		return cart;
	}
	public void setCart(Map<String, CartItem> cart) {
		this.cart = cart;
	}
	

	public double getPrice(){
		double totalprice = 0;
		for(Map.Entry<String, CartItem> me : cart.entrySet()){
			CartItem item = me.getValue();
			totalprice = totalprice + item.getPrice();
		}
		
		this.price = totalprice;
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void add(Book book){
		CartItem item = cart.get(book.getId());
		if(item == null){
			item = new CartItem();
			item.setBook(book);
			item.setQuantity(1);
			cart.put(book.getId(),item);
		}else{
			item.setQuantity(item.getQuantity()+1);
		}
	}
	
	
}
