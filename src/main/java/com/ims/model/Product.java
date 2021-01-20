package com.ims.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ims.exception.NotEnoughStockException;


@Entity
@Table(name="product")
public class Product  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private double price;
	private int qty;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brands brands;
	
	
	public Product() {
		
	}

	public Product(int id, String name, double price, int qty, Category category, Brands brands) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.category = category;
		this.brands = brands;
	}
	
	public void addStock(int qty) {
		this.qty += qty;
	}
	
	public void removeStock(int qty) {
		int restStock = this.qty -= qty;
		
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.qty = restStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brands getBrands() {
		return brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}
	
	public void removeQty(int qty) {
		int restQty = this.qty -= qty;
		this.qty = restQty;
	}
	
	public void addQty(int qty) {
		this.qty += qty;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", qty=" + qty + ", category=" + category
				+ ", brands=" + brands + "]";
	}
	
	
	
	
	

	
}
