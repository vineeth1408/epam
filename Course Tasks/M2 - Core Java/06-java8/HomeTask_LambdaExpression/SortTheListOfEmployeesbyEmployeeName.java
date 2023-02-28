package Throughinterfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

interface SortByEmployeeNameInList {
	
	public void withLamdaExpression(List<Employee> employees);
	public void withoutLamdaExpression(List<Employee> employees);
}
class SortTheListOfEmployeesbyEmployeeName implements SortByEmployeeNameInList {

	public static void main(String[] args) {
		
		List<Employee> listOfEmployees = getEmployees();
		SortTheListOfEmployeesbyEmployeeName sortTheListOfEmployeesbyEmployeeName = new SortTheListOfEmployeesbyEmployeeName();
		sortTheListOfEmployeesbyEmployeeName.withLamdaExpression(listOfEmployees);
		sortTheListOfEmployeesbyEmployeeName.withoutLamdaExpression(listOfEmployees);
	}
	public static List<Employee> getEmployees() {

		List<Employee> employees = new ArrayList<Employee>();
	
		employees.add(new Employee(1408, "vineeth"));
		employees.add(new Employee(1409,"rakesh"));
		employees.add(new Employee(1410, "anil"));
		employees.add(new Employee(1411, "arun"));
		employees.add(new Employee(1412, "monesh"));
		
		return employees;
	}
	@Override
	public void withLamdaExpression(List<Employee> employees) {
		
		System.out.println("With lambda expression\n");
		
		System.out.println("\nBefore Sorting\n");
		System.out.println(employees);
		
		Collections.sort(employees, (e1, e2)-> {
			return -e1.EmployeeName.compareTo(e2.EmployeeName);
		});
		
		System.out.println("\nAfter Sorting\n");
		System.out.println(employees);
	}
	@Override
	public void withoutLamdaExpression(List<Employee> employees) {
		
		System.out.println("\nWithout lambda expression\n");
		
		System.out.println("Before Sorting\n");
		System.out.println(employees);
		
		Collections.sort(employees, new SortByEmployeeNameComparator());;

		System.out.println("\nAfter Sorting\n");
		System.out.println(employees);
	}
}

class Employee {
	
	public int EmployeeId;
	public String EmployeeName;
	
	public Employee(int Id, String Name) {
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
class SortByEmployeeNameComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		
		return -e1.EmployeeName.compareTo(e2.EmployeeName);
	}
}
