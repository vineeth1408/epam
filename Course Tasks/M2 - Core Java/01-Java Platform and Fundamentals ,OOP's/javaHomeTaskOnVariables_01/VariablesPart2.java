package javaHomeTaskOnVariables_01;

public class VariablesPart2 {

	public static void main(String[] args) {
		Persons Dobj = new Persons("Danile",3.5f);
		Persons Aobj = new Persons("Amber",2.5f);
		System.out.println("Total Apples count is: "+(Dobj.apples+Aobj.apples));
	}
}
class Persons {
	String name;
	float apples;
	Persons(String name,float apples) {
		this.name=name;
		this.apples=apples;
	}
}
