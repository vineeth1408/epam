package demo;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Map;

class StreamsDemo {
	public static void main(String[] epam) {
		List<Product> productList = getProducts();
		productList.stream().forEach(System.out::println);

		System.out.println("After Filter 1: Products > 30000");
		List<Product> filteredData1 = productList.stream().filter( p -> p.price > 30000).collect(Collectors.toList());
		filteredData1.stream().forEach(System.out::println);
		filteredData1.stream().forEach(p -> System.out.println(p.name));

		List<Float> filteredData2 = productList.stream().filter(p -> p.price > 30000).map(p -> p.price+1).collect(Collectors.toList());
		System.out.println(filteredData2);


		System.out.println("Stream iterating");
		Stream.iterate(1, element -> element+1).filter(element -> element %5==0).limit(5).forEach(System.out::println);
		Stream.iterate(1, element -> element+1).limit(5).forEach(System.out::println);

		System.out.println("Reduce Method Example");
		productList.stream().map(p -> p.price).reduce(2000f, (sum, price)-> sum + price);
		System.out.println("Through MethodReferences Example");
		Float totalPrice = productList.stream().map(p -> p.price).reduce(2000f, Float::sum);
		System.out.println(totalPrice);

		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		int result1 = numbers.stream().reduce(0, (sum, element) -> sum + element);
		int result2 = numbers.stream().reduce(0, Integer::sum);
		System.out.println(result1+" "+result2);

		List<String> letters = Arrays.asList("Ab","Bc","Cd","De");
		System.out.println( letters.stream().reduce("", (partialString, string)-> partialString+string) );
		System.out.println( letters.stream().reduce("", String::concat) );
		System.out.println(letters.stream().reduce("", (partialString, element) -> partialString.toUpperCase() + element.toUpperCase()) );

		List<Integer> ages = Arrays.asList(5, 0,5, 0, 5);
		System.out.println(ages.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum));

		System.out.println("Products price reduce method");
		System.out.println(productList.stream().reduce(0f, (temp, productPrice) -> temp + productPrice.getPrice(), Float::sum));
		System.out.println(productList.stream().map(Product::getPrice).reduce(0f, Float::sum));

		System.out.println("Using Collectors Method");
		System.out.println(productList.stream().collect(Collectors.summingDouble(Product::getPrice)));
		System.out.println("Min and Max products");
		System.out.println( productList.stream().max((p1, p2) -> p1.price > p2.price ?1 :-1).get());
		System.out.println( productList.stream().min((p1, p2) -> p1.price > p2.price ?1 :-1).get());
		

		List<Integer> marks = Arrays.asList(1,2,3,4,5); 
		System.out.println(marks.stream().min((i1,i2)->i1.compareTo(i2)).get());
		System.out.println(marks.stream().max((i1,i2)->i1.compareTo(i2)).get());

		System.out.println("Converting to Set");
		Set<Float> prices = productList.stream().map(p -> p.price).collect(Collectors.toSet());
		System.out.println(prices);
		System.out.println("Converting to Map");
		Map<Integer,String> map1 = productList.stream().collect(Collectors.toMap(p -> p.id, p ->p.name));
		Map<Integer,String> map2 = productList.stream().collect(Collectors.toMap(Product::getId,Product::getName));
		System.out.println(map2);

		System.out.println("Flat Map");
		List<String> productlist1 = Arrays.asList("Printer", "Mouse", "Keyboard", "Motherboard");  
		List<String>  productlist2 = Arrays.asList("Scanner", "Projector", "Light Pen");  
		List<List<String>> allproducts = new ArrayList<List<String>>();   
		//adding elements to the list  
		allproducts.add(productlist1);  
		allproducts.add(productlist2);  
		
		List<String> flatMapList = allproducts.stream().flatMap( plist -> plist.stream()).collect(Collectors.toList());
		List<String> flatMapList2 = allproducts.stream().flatMap(Collection::stream).collect(Colectors.toList());
		System.out.println(flatMapList);

		ArrayList<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		
		
		ArrayList<Integer> l2 = new ArrayList<>();
		l2.add(4);
		l2.add(5);
		l2.add(6);
		
		List<Integer[]> p = l1.stream().flatMap( a -> l2.stream().map(b -> new Integer[]{a,b})).collect(Collectors.toList());
		p.forEach( pair -> System.out.println(pair[0] +" "+pair[1]));





	}
	public static List<Product> getProducts() {
		return Arrays.asList( new Product(1,"HP Laptop",25000f),
							  new Product(2,"Dell Laptop",30000f),
							  new Product(3,"Lenevo Laptop",28000f), 
							  new Product(4,"Sony Laptop",28000f), 
							  new Product(5,"Apple Laptop",90000f) );
	}
}

class Product{  
    int id;  
    String name;  
    float price;  
    public Product(int id, String name, float price) {  
        this.id = id;  
        this.name = name;  
        this.price = price;  
    } 
    public String toString() {
    	return this.id+" "+this.name+" "+this.price;
    }
    public float getPrice() {
    	return this.price;
    }
    public int getId(){
    	return this.id;
    } 
    public String getName(){
    	return this.name;
    }
}  
