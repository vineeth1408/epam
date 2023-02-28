package Throughinterfaces;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

interface AlphabeticalSort {
	
	public void withOutLambdaExpression();
	public void withLambdaExpression();
}
class AlphabeticalSortUsingTreeSet implements AlphabeticalSort {

	public static void main(String[] args) {
		AlphabeticalSortUsingTreeSet alphabeticalSortUsingTreeSet = new AlphabeticalSortUsingTreeSet();
		alphabeticalSortUsingTreeSet.withLambdaExpression();
		System.out.println();
		alphabeticalSortUsingTreeSet.withOutLambdaExpression();
	}
	@Override
	public void withOutLambdaExpression() {
		
		Set<EmployeeDetails> setOfDetails = new TreeSet<EmployeeDetails>(new MyownComparator());

		setOfDetails.add(new EmployeeDetails(100,"naresh"));
		setOfDetails.add(new EmployeeDetails(101,"suresh"));
		setOfDetails.add(new EmployeeDetails(102,"vineeth"));
		setOfDetails.add(new EmployeeDetails(103,"pavan"));
		setOfDetails.add(new EmployeeDetails(104,"anil"));
		setOfDetails.add(new EmployeeDetails(105,"monesh"));
		
		for(EmployeeDetails employee: setOfDetails) {
			System.out.println(employee);
		}
		
	}
	@Override
	public void withLambdaExpression() {
		
		TreeSet<EmployeeDetails> setOfDetails = new TreeSet<EmployeeDetails>
		( (e1, e2) -> { return e1.EmployeeName.compareTo(e2.EmployeeName); });
		
		setOfDetails.add(new EmployeeDetails(100,"naresh"));
		setOfDetails.add(new EmployeeDetails(101,"suresh"));
		setOfDetails.add(new EmployeeDetails(102,"vineeth"));
		setOfDetails.add(new EmployeeDetails(103,"pavan"));
		setOfDetails.add(new EmployeeDetails(104,"anil"));
		setOfDetails.add(new EmployeeDetails(105,"monesh"));
		
		for(EmployeeDetails employee: setOfDetails) {
			System.out.println(employee);
		}
	}
}
class EmployeeDetails {
	String EmployeeName;
	int EmployeeId;

	EmployeeDetails(int id, String name) {
	this.EmployeeId = id;
	this.EmployeeName = name;
}

public String getEmployeeName() {
	return EmployeeName;
}

public void setEmployeeName(String employeeName) {
	EmployeeName = employeeName;
}

@Override
public String toString() {
	return " [EmployeeName = " + EmployeeName + ", EmployeeId = " + EmployeeId + "]";
	}
}

class MyownComparator implements Comparator<EmployeeDetails> {

@Override
public int compare(EmployeeDetails e1, EmployeeDetails e2) {
	return e1.EmployeeName.compareTo(e2.EmployeeName);
	}
}

