package com.ims.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String price;
	private String qty;
	private String image;
	
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brands brand;
	
	@OneToMany
	@JoinTable(name = "attribute_value",
	joinColumns=@JoinColumn(name = "id"),
	inverseJoinColumns = @JoinColumn(name="attribute_id")
	)
	public Set<Attribute> attribute = new HashSet<>();
	
	public Product() {
		
	}
	
	
	public Product(int id, String name, String price, String qty, String image,
			Category category, Brands brand,Set<Attribute> attribute) {
		
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.image = image;
		this.category = category;
		this.brand = brand;
		this.attribute = attribute;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}

	public Set<Attribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(Set<Attribute> attribute) {
		this.attribute = attribute;
	}
	
	
	
	
}
