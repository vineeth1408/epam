package oops_Home_Tak_7_01;
import java.util.*;

public class BouquetOfFlowers_2 {

	public static void main(String[] args) {
		
		Flower Lotus = new Flower("Lotus","White",10);
		Flower Jasmine = new Flower("Jasmine","Yellow",20);
		Flower Rose = new Flower("Rose","Pink",30);
		
		Bouquet bouquet1 = new Bouquet();
		Bouquet bouquet2 = new Bouquet();
		
		BouquetOperations bouquetOperations = new BouquetOperations();
		
		bouquet1.addFlower(Rose);
		bouquet1.addFlower(Jasmine);
		bouquet1.addFlower(Lotus);
		
		bouquet2.addFlower(Jasmine);
		bouquet2.addFlower(Lotus);
		
		bouquetOperations.printFlowersInBoquet(bouquet1);
		bouquetOperations.printFlowersInBoquet(bouquet2);
		
	
	}
}

class Flower {
	
	private String NameOfTheFlower;
	private String FlowerColor;
	private double CostOfFlower;
	
	Flower(String NameOfTheFlower,String FlowerColor,double CostOfFlower){
		this.CostOfFlower = CostOfFlower;
		this.FlowerColor = FlowerColor;
		this.NameOfTheFlower = NameOfTheFlower;
	}
	
	public String getFlowerName() {
		return this.NameOfTheFlower;
	}
	public String getFlowerColor() {
		return this.FlowerColor;
	}
	public double getCostOfFlower() {
		return this.CostOfFlower;
	}
}
class BouquetOperations {
	
	public void printFlowersInBoquet(Bouquet bouquet) {
		if (bouquet.getFlowers().size() <= 0) {
			return;
		}
		System.out.println("FLOWER NAME "+"COLOR "+"COST \n");
		for (Flower flower : bouquet.getFlowers()) {
			System.out.println(flower.getFlowerName()+" "+flower.getFlowerColor()+" "+flower.getCostOfFlower());
		}
		System.out.println("Total cost of the bouquet ="+ bouquet.getCost());
	}
//	public void setCustomCost(Bouquet bouquet, double cost) { // To set customcost to the bouquet
//		if(bouquet.getCost() == 0) {
//			bouquet.setCost(cost);
//		}
//	}
}
class Bouquet {
	
	private Vector<Flower> flowers;
	private double cost;
	
	Bouquet() {
		flowers = new Vector<>();
		this.cost = 0;
	}
	public void addFlower(Flower flower) {
		flowers.add(flower);
		cost += flower.getCostOfFlower();
	}
	public Vector<Flower> getFlowers() {
		return flowers;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getCost() {
		return this.cost;
	}
}
