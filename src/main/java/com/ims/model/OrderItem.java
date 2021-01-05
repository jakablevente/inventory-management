package com.ims.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem implements java.io.Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -471515790110981708L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "order_id")
	    private Orders order;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id")
	    private Product product;
	    
	    private int qty;
	    private double amount;
	    
	    
	    public OrderItem() {}
	    
	    public static OrderItem createOrderItem(Product product,  double amount,int qty) {
	    	OrderItem orderItem = new OrderItem();
	    	orderItem.setProduct(product);
	    	orderItem.setQty(qty);
	    	orderItem.setAmount(amount);
	    	
	    	
	    	
	    	return orderItem;
	    }
	    
	    public double getTotalPrice() {
	    	return getAmount() * getQty();
	    }
	    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
}
