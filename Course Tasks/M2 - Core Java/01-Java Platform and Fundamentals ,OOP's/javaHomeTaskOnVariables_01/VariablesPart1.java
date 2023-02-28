package javaHomeTaskOnVariables_01;

public class VariablesPart1 {

	public static void main(String[] args) {
		Person Dobj = new Person("Danile",3);
		Person Aobj = new Person("Amber",2);
		System.out.println("Total Apples count is: "+(Dobj.apples+Aobj.apples));
	}
}
class Person {
	String name;
	int apples;
	Person(String name,int apples) {
		this.name = name;
		this.apples = apples;
	}
}

