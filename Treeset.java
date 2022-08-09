package demo;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
public class Treeset {
	public static void main(String[] epam) {
		Set<Employee1> treeset = new TreeSet<>();
		treeset.add(new Employee1(1,"vineeth",1000));
		treeset.add(new Employee1(2,"suresh",2000));
		for(Employee1 e: treeset) {
			System.out.println(e.Id+" "+e.Name+" "+e.Salary);
		}
	}
}
class Employee1 implements Comparable<Employee1> {
	public int Id;
	public String Name;
	public int Salary;

	public Employee1(int Id, String Name, int Salary) {
		this.Id = Id;
		this.Name = Name;
		this.Salary = Salary;
	}
	public int compareTo(Employee1 e) {
		// if (Id > e.Salary) {
		// 	return 1;
		// }
		// else if(Id < e.Id) {
		// 	return -1;
		// }
		// else {
		// 	return 0;
		// }
		return Salary < e.Salary ?1:Salary > e.Salary ?-1 :0;
	}
}
