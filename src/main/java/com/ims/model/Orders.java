package com.ims.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="orders")
public class Orders implements java.io.Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 2297381202194678732L;

		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	    
		@Column(name = "date_time")
		@CreatedDate
	    private Date dateTime;
		
	    private double total;
	    @Column(name = "paid_status")
	    private int paidStatus;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="customer_id")
	    private Customers customer;
	    

		@OneToMany(mappedBy="order",cascade=CascadeType.ALL,
		orphanRemoval = true)
	    private List<OrderItem> orderItems = new ArrayList<>();

		
		public static Orders createOrder(Customers customer, OrderItem... orderItems) {
			Orders order = new Orders();
			order.setCustomer(customer);
			order.setPaidStatus(1);
			order.setDateTime(new Date());
			
			for(OrderItem orderItem : orderItems) {
				order.addOrderItem(orderItem);
			}
				
			
			return order;
		}

		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public Date getDateTime() {
			return dateTime;
		}


		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}


		public double getTotal() {
			return total;
		}


		public void setTotal(double total) {
			this.total = total;
		}


		public int getPaidStatus() {
			return paidStatus;
		}


		public void setPaidStatus(int paidStatus) {
			this.paidStatus = paidStatus;
		}


		public Customers getCustomer() {
			return customer;
		}


		public void setCustomer(Customers customer) {
			this.customer = customer;
		}


		public List<OrderItem> getOrderItems() {
			return orderItems;
		}


		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}

		public void addOrderItem( OrderItem orderItem){
			orderItems.add(orderItem);
			orderItem.setOrder(this);
		}
		
		public void removeOrderItem(OrderItem orderItem) {
			
			orderItem.setOrder(null);
			this.orderItems.remove(orderItem);
		}
		
		public double getTotalPrice() {
			double totalPrice = 0;
			
			for(OrderItem orderItem : orderItems) {
				totalPrice += orderItem.getTotalPrice();
			}
			
			return totalPrice;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
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
			Orders other = (Orders) obj;
			if (orderItems == null) {
				if (other.orderItems != null)
					return false;
			} else if (!orderItems.equals(other.orderItems))
				return false;
			return true;
		}
			

}
