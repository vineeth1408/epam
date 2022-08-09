package demo;
public class Employee {
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