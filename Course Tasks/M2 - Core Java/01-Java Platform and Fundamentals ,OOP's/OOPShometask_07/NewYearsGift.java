package oops_Home_Tak_7_01;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewYearsGift {

	public static void main(String[] args) {
		
		
		MilkChocolate milkChocolate = new MilkChocolate(5, 5.2,"Milk Chocolates");
		DarkChocolate darkChocolate = new DarkChocolate(10, 4.5,"Dark Chocolates");
		Barfi barfi = new Barfi(15, 10.0,"Barfi");
		Laddu laddu = new Laddu(5, 15.0, "Laddu");
		
		GiftBox giftBox1 = new GiftBox();
		
		giftBox1.addSweet(darkChocolate);
		giftBox1.addSweet(milkChocolate);
		giftBox1.addSweet(laddu);
		giftBox1.displayItems();
		System.out.println();
				
		GiftBox giftBox2 = new GiftBox();
		giftBox2.addSweet(darkChocolate);
		giftBox2.addSweet(milkChocolate);
		giftBox2.addSweet(laddu);
		giftBox2.addSweet(barfi);
		giftBox2.displayItems();
		
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("After sorting giftbox - 1\n");
		giftBox1.sort(); // sorting through sweetname
		System.out.println("After sorting giftbox - 2\n");
		giftBox2.sort();
	}		
}

class Sweets {
	
	int NumberOfItems;
	double costPerItem;
	String sweetName;
	Sweets(int count, double cost, String name) {
		this.NumberOfItems = count;
		this.costPerItem = cost;
		this.sweetName = name;
	}
	@Override
	public String toString() {
		return "Sweets [NumberOfItems =" + NumberOfItems + ", costPerItem =" + costPerItem + ", sweetName =" + sweetName
				+ "]";
	}
	public String getSweetNmae() {
		return this.sweetName;
	}
}
class MilkChocolate extends Sweets {
	
	MilkChocolate(int NumberOfItems, double costPerItem, String name) {
		super(NumberOfItems, costPerItem, name);
	}
}
class DarkChocolate extends Sweets {
	
	DarkChocolate(int NumberOfItems, double costPerItem, String name) {
		super(NumberOfItems, costPerItem, name);
	}
}
class Barfi extends Sweets {
	
	Barfi(int NumberOfItems, double costPerItem, String name) {
		super(NumberOfItems, costPerItem, name);
	}
}

class Laddu extends Sweets {
	
	Laddu(int NumberOfItems, double costPerItem, String name) {
		super(NumberOfItems, costPerItem, name);
	}
}
class GiftBox {
	
	List<Sweets> sweets;
	double weight;
	GiftBox() {
		sweets = new ArrayList<>();
		this.weight = (int)(Math.random()*10); // generating random number
	}
	public void addSweet(Sweets Sweet) {
		sweets.add(Sweet);
	}
	public void displayItems() {
		sweets.forEach(System.out::println);
		System.out.println("Weight of the gift box= "+this.weight);
	}
	public void sort() {
		List<Sweets> sorts = sweets.stream().sorted((s1, s2)-> s1.sweetName.compareTo(s2.sweetName)).collect(Collectors.toList());
		sorts.forEach(System.out::println);
	}
}