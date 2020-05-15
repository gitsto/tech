package fr.orsys.jva.exercice2;

public class Car {
	private String name;
	private Double price;
	public Car() {
		// TODO Auto-generated constructor stub
	}
	
	public Car(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", price=" + price + "]";
	}

	
}
