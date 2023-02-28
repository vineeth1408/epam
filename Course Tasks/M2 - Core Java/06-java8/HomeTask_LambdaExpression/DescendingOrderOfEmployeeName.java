package Throughinterfaces;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

interface SortByEmployeeName {
	
	public void withOutLambdaExpression();
	public void withLambdaExpression();
}
public class DescendingOrderOfEmployeeName implements SortByEmployeeName {

	public static void main(String[] args) {
		
		DescendingOrderOfEmployeeName descendingOrderOfEmployeeName = new DescendingOrderOfEmployeeName();
		descendingOrderOfEmployeeName.withLambdaExpression();
		System.out.println();
		descendingOrderOfEmployeeName.withOutLambdaExpression();
	}
	@Override
	public void withOutLambdaExpression() {
		
		Map<EmployeeDetail, Integer> setOfEmployees = new TreeMap<EmployeeDetail, Integer>(new SortByNameComparator());
				
		setOfEmployees.put(new EmployeeDetail(1003, "Dhawan"),3);
		setOfEmployees.put(new EmployeeDetail(1004, "Rohit"),4);
		setOfEmployees.put(new EmployeeDetail(1005, "Dhoni"),5);
		setOfEmployees.put(new EmployeeDetail(1001, "Kohli"),1);
		setOfEmployees.put(new EmployeeDetail(1002, "Rahul"),2);
		
		for(Map.Entry<EmployeeDetail, Integer> m : setOfEmployees.entrySet()) {
			System.out.println(m.getKey()+" "+m.getValue());
		}
		
	}	
	@Override
	public void withLambdaExpression() {
		
		Map<EmployeeDetail, Integer> setOfEmployees = new TreeMap<EmployeeDetail, Integer> (
			(e1, e2) -> { return -e1.EmployeeName.compareTo(e2.EmployeeName);}
		);
		
		setOfEmployees.put(new EmployeeDetail(1001, "Kohli"),1);
		setOfEmployees.put(new EmployeeDetail(1002, "Rahul"),2);
		setOfEmployees.put(new EmployeeDetail(1003, "Dhawan"),3);
		setOfEmployees.put(new EmployeeDetail(1004, "Rohit"),4);
		setOfEmployees.put(new EmployeeDetail(1005, "Dhoni"),5);
		
		System.out.println(setOfEmployees);
	}
}

	
class EmployeeDetail {
	
	int EmployeeId;
	String EmployeeName;
	EmployeeDetail(int id, String name) {
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
		return "EmployeeDetail [EmployeeId= " + EmployeeId + ", EmployeeName= " + EmployeeName + "]";
	}
}

class SortByNameComparator implements Comparator<EmployeeDetail> {

	@Override
	public int compare(EmployeeDetail e1, EmployeeDetail e2) {
		return -e1.EmployeeName.compareTo(e2.EmployeeName);
	}
}

