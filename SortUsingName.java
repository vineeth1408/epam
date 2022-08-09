package demo;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.Comparator;
public class SortUsingName {

	public static void main(String[] args) {

		List<Employee> employees = getEmployees();
		System.out.println("Before Sorting the employees");
		System.out.println(employees);

		// Collections.sort(employees, (e1,e2) -> {
		// 	return -e1.Name.compareTo(e2.Name);
		// });
		Comparator<Employee> sorter = (e1,e2) -> e1.Name.compareTo(e2.Name);
		Collections.sort(employees, sorter);		
		System.out.println("After Sorting the employees");
	
		//employees.stream().sorted((e1,e2) -> e1.Name.compare(e2.Name)).forEach(System.out::println);
		
		for(Employee employee: employees) {
				System.out.println(employee);
		}
	}
	public static List<Employee> getEmployees() {
		// List<Employee> employees = new ArrayList<>();
		// employees.add(new Employee(1,"vineth",100000));
		// employees.add(new Employee(2,"naresh",200000));
		// employees.add(new Employee(3,"suresh",300000));
		// employees.add(new Employee(4,"kavya",400000));
		// employees.add(new Employee(5,"sravya",500000));

		return Arrays.asList(new Employee(1,"vineth",100000),
							new Employee(2,"naresh",200000),
							new Employee(3,"suresh",300000),
							new Employee(4,"kavya",400000));

	}
}
class Employee {
	public int Id;
	public String Name;
	public int Salary;

	public Employee(int Id, String Name, int Salary) {
		this.Id = Id;
		this.Name = Name;
		this.Salary = Salary;
	}
	public int getId() {
		return this.Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}

	public String getName() {
		return this.Name;
	}
	public void setName(String name) {
		this.Name = name;
	}

	public int getSalary() {
		return this.Salary;
	}
	public void setSalary(int salary) {
		this.Salary = salary;
	}

	public String toString() {
		return this.Id+" "+this.Name+" "+this.Salary;
	}
}