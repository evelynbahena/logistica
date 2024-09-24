package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_product")
	private Integer idProduct;

	private String description;

	private String name;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="product_categories"
		, joinColumns={
			@JoinColumn(name="id_product")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_category")
			}
		)
	private List<Category> categories;

	//bi-directional many-to-one association to ProductsRemmision
	@OneToMany(mappedBy="product")
	private List<ProductsRemmision> productsRemmisions;

	//bi-directional many-to-one association to ProductsRequisition
	@OneToMany(mappedBy="product")
	private List<ProductsRequisition> productsRequisitions;

	//bi-directional many-to-one association to StockProduct
	@OneToMany(mappedBy="product")
	private List<StockProduct> stockProducts;

	public Product() {
	}

	public Integer getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<ProductsRemmision> getProductsRemmisions() {
		return this.productsRemmisions;
	}

	public void setProductsRemmisions(List<ProductsRemmision> productsRemmisions) {
		this.productsRemmisions = productsRemmisions;
	}

	public ProductsRemmision addProductsRemmision(ProductsRemmision productsRemmision) {
		getProductsRemmisions().add(productsRemmision);
		productsRemmision.setProduct(this);

		return productsRemmision;
	}

	public ProductsRemmision removeProductsRemmision(ProductsRemmision productsRemmision) {
		getProductsRemmisions().remove(productsRemmision);
		productsRemmision.setProduct(null);

		return productsRemmision;
	}

	public List<ProductsRequisition> getProductsRequisitions() {
		return this.productsRequisitions;
	}

	public void setProductsRequisitions(List<ProductsRequisition> productsRequisitions) {
		this.productsRequisitions = productsRequisitions;
	}

	public ProductsRequisition addProductsRequisition(ProductsRequisition productsRequisition) {
		getProductsRequisitions().add(productsRequisition);
		productsRequisition.setProduct(this);

		return productsRequisition;
	}

	public ProductsRequisition removeProductsRequisition(ProductsRequisition productsRequisition) {
		getProductsRequisitions().remove(productsRequisition);
		productsRequisition.setProduct(null);

		return productsRequisition;
	}

	public List<StockProduct> getStockProducts() {
		return this.stockProducts;
	}

	public void setStockProducts(List<StockProduct> stockProducts) {
		this.stockProducts = stockProducts;
	}

	public StockProduct addStockProduct(StockProduct stockProduct) {
		getStockProducts().add(stockProduct);
		stockProduct.setProduct(this);

		return stockProduct;
	}

	public StockProduct removeStockProduct(StockProduct stockProduct) {
		getStockProducts().remove(stockProduct);
		stockProduct.setProduct(null);

		return stockProduct;
	}

}