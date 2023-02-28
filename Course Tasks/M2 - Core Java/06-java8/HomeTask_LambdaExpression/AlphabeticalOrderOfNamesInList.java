package Throughinterfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

interface AlphabeticalOrder {
	
	public void withLamdaExpression(List<EmployeeInfo> employees);
	public void withoutLamdaExpression(List<EmployeeInfo> employees);
}

class AlphabeticalOrderOfNamesInList implements AlphabeticalOrder {

	public static void main(String[] args) {

		List<EmployeeInfo> listOfEmployees = getEmployees();
		AlphabeticalOrderOfNamesInList alphabeticalOrderOfNamesInList = new AlphabeticalOrderOfNamesInList();
		alphabeticalOrderOfNamesInList.withLamdaExpression(listOfEmployees);
		alphabeticalOrderOfNamesInList.withoutLamdaExpression(listOfEmployees);
	}
	public static List<EmployeeInfo> getEmployees() {

		List<EmployeeInfo> employees = new ArrayList<EmployeeInfo>();
	
		employees.add(new EmployeeInfo(1408, "vineeth"));
		employees.add(new EmployeeInfo(1409,"rakesh"));
		employees.add(new EmployeeInfo(1410, "anil"));
		employees.add(new EmployeeInfo(1411, "arun"));
		employees.add(new EmployeeInfo(1412, "monesh"));
		
		return employees;
	}
	@Override
	public void withLamdaExpression(List<EmployeeInfo> employees) {
		
		System.out.println("With lambda expression\n");
		
		System.out.println("\nBefore Ascending\n");
		System.out.println(employees);
		
		// employees.sort(Comparator.comparing(e -> e.getEmployeeName()));
		Collections.sort(employees, (e1, e2)-> {
			return e1.EmployeeName.compareTo(e2.EmployeeName);
		});
		
		System.out.println("\nAfter Ascending\n");
		System.out.println(employees);
	}
	@Override
	public void withoutLamdaExpression(List<EmployeeInfo> employees) {
		
		System.out.println("\nWithout lambda expression\n");
		
		System.out.println("Before Ascending\n");
		System.out.println(employees);
		
		Collections.sort(employees, new SortByEmployName());;

		System.out.println("\nAfter Ascending\n");
		System.out.println(employees);
	}
}

class EmployeeInfo {
	
	public int EmployeeId;
	public String EmployeeName;
	
	public EmployeeInfo(int Id, String Name) {
		this.EmployeeId = Id;
		this.EmployeeName = Name;
	}
	
	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String toString() {
		
		return this.EmployeeId+" "+this.EmployeeName;
	}
}
class SortByEmployName implements Comparator<EmployeeInfo> {

	@Override
	public int compare(EmployeeInfo e1, EmployeeInfo e2) {
		
		return e1.EmployeeName.compareTo(e2.EmployeeName);
	}
}

