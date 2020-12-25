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

@Entity
@Table(name="orders")
public class Orders {
		
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	    
		@Column(name = "date_time")
	    private Date dateTime;
		
	    private double total;
	    @Column(name = "paid_status")
	    private int paidStatus;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="customer_id")
	    private Customers customer;
	    

	    @OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	    private List<OrderItem> orderItems = new ArrayList<>();


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
			this.dateTime = new Date();
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



    



	
	
	
	

}
