package hometask_09_collections;
import java.util.Map;
import java.util.HashMap;

public class StudentDetailsUsingHashMap {

	public static void main(String[] args) {
		HashMap<Integer,StudentInfo> map=new HashMap<Integer,StudentInfo>();
		 
		 StudentInfo naresh=new StudentInfo("naresh",1000,5,15);
		 StudentInfo suresh=new StudentInfo("suresh",1000,5,15);
		 StudentInfo vineeth=new StudentInfo("vineeth",1000,5,16);
		 
		 map.put(1, vineeth);
		 map.put(3, suresh);
		 map.put(2, naresh);
		 
		 for(Map.Entry<Integer, StudentInfo> entry:map.entrySet()) {
			 int key=entry.getKey();
			 StudentInfo s=entry.getValue();
			 System.out.println(key+" Student Details:");  
			 System.out.println("Name "+s.name+" rollno "+s.rollno+" class "+s.className+" age "+s.age);

		 }
	}
}
class StudentInfo{
	String name;
	int rollno;
	int className;
	int age;
	StudentInfo(String name,int rollno,int className,int age){
		this.className=className;
		this.name=name;
		this.rollno=rollno;
		this.age=age;
	}
}