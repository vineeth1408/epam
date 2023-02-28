package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsInfo {

	public static void main(String[] args) {
		
		List<Product> listOfProducts = Arrays.asList(
				new Product("Mobile phone",1000,"Electronic","5 STAR"),
				new Product("Laptop",20000,"Electronic","5 STAR"),
				new Product("Tablet",10000,"Electronic","4 STAR"),
				new Product("Speakers",2000,"Electronic","3 STAR"),
				
				new Product("Mugs",100,"Homwware","5 STAR"),
				new Product("Pillows",200,"Homwware","3 STAR"),
				new Product("Wall Art",250,"Homwware","2 STAR"),
				new Product("Bedding",300,"Homwware","5 STAR"),
				
				new Product("Car",5000,"Vehicle","3 STAR"),
				new Product("Bus",6000,"Vehicle","5 STAR"),
				new Product("Bike",1000,"Vehicle","5 STAR"),
				new Product("Train",2000,"Vehicle","5 STAR")
				);
		
		System.out.println("Products With price  greater than 1000 ");
		List<Product> priceAbove1000 = listOfProducts.stream().filter(p->p.price>1000).collect(Collectors.toList());
		priceAbove1000.forEach(System.out::println);
		System.out.println();
		
		System.out.println("Products With Electronic Category ");
		List<Product> electronicGategory = listOfProducts.stream().filter(p->p.Category.equals("Electronic")).collect(Collectors.toList());
		electronicGategory.forEach(System.out::println);
		System.out.println();
		
		System.out.println("Products With Electronic Category and greater than 1000 ");
		List<Product> electronicCategoryWithPriceAbove1000 = listOfProducts.stream().filter(p->p.Category.equals("Electronic") && p.price >1000).collect(Collectors.toList());
		electronicCategoryWithPriceAbove1000.forEach(System.out::println);
		System.out.println();
		
		System.out.println("Products With Electronic Category Count ");
		long countOfElectronicProducts = listOfProducts.stream().filter(p->p.Category.equals("Electronic")).count();
		System.out.println(countOfElectronicProducts);
	}
}

class Product {
	String ProductName;
	double price;
	String Category;
	String Grade;
	
	Product(String ProductName, double price, String Category, String Grade) {
		this.ProductName = ProductName;
		this.price = price;
		this.Category = Category;
		this.Grade = Grade;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	@Override
	public String toString() {
		return "Product [ProductName=" + ProductName + ", price=" + price + ", Category=" + Category + ", Grade="
				+ Grade + "]";
	}
}
