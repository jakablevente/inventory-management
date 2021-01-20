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
	    
	    public OrderItem(Integer id, Orders order, Product product, int qty, double amount) {
	    	
	    	super();
	    	this.id = id;
	    	this.order = order;
	    	this.product = product;
	    	this.qty = qty;
	    	this.amount = amount;
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
	public void setAmount(int qty, double price ){
		this.amount = qty * price;
	}
	
	public void setAmount(double price) {
		this.amount = price;
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + qty;
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
		OrderItem other = (OrderItem) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (id != other.id)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (qty != other.qty)
			return false;
		return true;
	}
	
	
	
	
	
	
}
