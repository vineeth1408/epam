package hometask_09_collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

interface DemonstrationOfCollections {
	
	public void demonstration();
}

class Arraylist implements DemonstrationOfCollections {

	@Override
	public void demonstration() {
		List<Integer> marks = new ArrayList<Integer>();
		
		for (int i = 0; i < 1E5; i++) {
			marks.add(i);
		}
		
		System.out.println("ArrayList Demonstratoin\n");
		for (int i = 0; i < 15; i++) {
			System.out.print(marks.get(i)+" ");
		}
		System.out.println("\n");
	}
}

class Linkedlist implements DemonstrationOfCollections {

	@Override
	public void demonstration() {
		List<String> names = new LinkedList<String>();
		
		names.add("vineeth");
		names.add("suresh");
		names.add("anil");
		names.add("arun");
		names.add("anup");
		
		System.out.println("LinkedList Demonstratoin\n");
		
		for(String name : names) {
			System.out.print(name+" ");
		}
		System.out.println("\n");
	}
}

class Hashmap implements DemonstrationOfCollections {

	@Override
	public void demonstration() {
		
		HashMap<Integer,String> map= new HashMap<Integer,String>();
		map.put(1, "vineeth");
		map.put(2, "naresh");
		map.put(3, "suresh");
		map.put(4, "tharun");
		System.out.println("Hashmap Demonstratoin\n");
		// System.out.println(map);
		for(Map.Entry m: map.entrySet()) {
			System.out.println(m.getKey()+" "+m.getValue());
		}
		System.out.println("\n");
	}	
}

class LinkedHashmap implements DemonstrationOfCollections {

	@Override
	public void demonstration() {
		Map<Integer,String> hm=new LinkedHashMap<Integer,String>();  
		
		hm.put(1, "vineeth");
		hm.put(2,"naresh");
		hm.put(3, "tharun");
		hm.put(4, "rahul");
		
		System.out.println("Linkedhashmap Demonstratoin\n");
		//System.out.println(hm);
		for(Map.Entry m:hm.entrySet()) {
			System.out.println(m.getKey()+" "+m.getValue());
		}
		System.out.println("\n");
	}
}

class Hashset implements DemonstrationOfCollections {

	@Override
	public void demonstration() {	
		 Set<Employees> set=new HashSet();  
         
		 Employees naresh=new Employees("naresh",10000);
		 Employees vineeth=new Employees("vineeth",10000);
		 Employees suresh=new Employees("suresh",10000);
		 
		 set.add(suresh);
		 set.add(vineeth);
		 set.add(naresh);
		 System.out.println("Hashset Demonstratoin\n");
		 for(Employees e:set) {
			 System.out.println(e.name+" "+e.salary);
		 } 
		 System.out.println("\n");
	}
}

class LinkedHashset implements DemonstrationOfCollections {

	@Override
	public void demonstration() {
		 Set<Employees> hs=new LinkedHashSet<Employees>();
		 
		 Employees naresh=new Employees("naresh",10000);
		 Employees vineeth=new Employees("vineeth",10000);
		 Employees suresh=new Employees("suresh",10000);
		 
		 hs.add(suresh);
		 hs.add(vineeth);
		 hs.add(naresh);
		 
		 System.out.println("LinkedHashSet Demonstratoin\n");
		 for(Employees e : hs) {
			 System.out.println(e.name+" "+e.salary);
		 }
		 System.out.println("\n");
	}
}

class Treeset implements DemonstrationOfCollections {

	@Override
	public void demonstration() {
		 Set<EmployeeDetails> set=new TreeSet<EmployeeDetails>();
		 
		 EmployeeDetails naresh = new EmployeeDetails("naresh","java developer",1);
		 EmployeeDetails suresh = new EmployeeDetails("suresh","java expert",10);
		 EmployeeDetails vineeth = new EmployeeDetails("vineeth","java beginner",3);
		 
		 set.add(suresh);
		 set.add(vineeth);
		 set.add(naresh);
		 
		 System.out.println("TreeSet Demonstratoin\n");
		 for(EmployeeDetails e:set) {
			 System.out.println(e.name+" "+e.role+" "+e.id);
		 }
		 System.out.println("\n");
	}
}

class Treemap implements DemonstrationOfCollections {

	@Override
	public void demonstration() {
		Map<Integer,StudentDetails> map=new TreeMap<Integer,StudentDetails>();
		 
		 StudentDetails naresh=new StudentDetails("naresh",1000,5);
		 StudentDetails suresh=new StudentDetails("suresh",1000,5);
		 StudentDetails vineeth=new StudentDetails("vineeth",1000,5);
		 
		 map.put(1, vineeth);
		 map.put(3, suresh);
		 map.put(2, naresh);
		 
		 System.out.println("TreeMap Demonstratoin\n");
		 for(Map.Entry<Integer, StudentDetails> entry:map.entrySet()) {
			 int key=entry.getKey();
			 StudentDetails s=entry.getValue();
			 System.out.println(key+" Details:");  
			 System.out.println("Name "+s.name+" rollno "+s.rollno+" class "+s.className);
		 }
	}
}
public class CollectionsDemonstration {

	public static void main(String[] args) {
		
		Arraylist arraylist = new Arraylist();
		arraylist.demonstration();
		
		Linkedlist linkedlist = new Linkedlist();
		linkedlist.demonstration();
		
		Hashmap hashmap = new Hashmap();
		hashmap.demonstration();
		
		LinkedHashmap linkedHashmap = new LinkedHashmap();
		linkedHashmap.demonstration();
		
		Hashset hashset = new Hashset();
		hashset.demonstration();
		
		LinkedHashset linkedHashset = new LinkedHashset();
		linkedHashset.demonstration();
		
		Treeset treeset = new Treeset();
		treeset.demonstration();
		
		Treemap treemap = new Treemap();
		treemap.demonstration();
	}
}

class Employees {
	String name;
	double salary;
	Employees(String name,double salary){
		this.salary=salary;
		this.name=name;
	}
}
class EmployeeDetails implements Comparable<EmployeeDetails>{
	String name;
	String role;
	int id;
	EmployeeDetails(String name,String role,int id){
		this.id=id;
		this.name=name;
		this.role=role;
	}
	public int compareTo(EmployeeDetails e) {
		if (id > e.id)
			return 1;
		else if (id < e.id)
			return -1;
		else
			return 0;
	}
}
class StudentDetails {
	String name;
	int rollno;
	int className;
	StudentDetails(String name,int rollno,int className){
		this.className=className;
		this.name=name;
		this.rollno=rollno;
	}
}

