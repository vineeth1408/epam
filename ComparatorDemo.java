package demo;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Collections;


class ComparatorDemo {
	public static void main(String[] epam) {
		List<Student1> students = getStudents();

		System.out.println("Before Sorting");
		System.out.println("ID NAME MARKS AGE\n");
		students.stream().forEach(System.out::println);

		System.out.println("After AgeComparator Sort");
		System.out.println("ID NAME MARKS AGE\n");
		Collections.sort(students, new AgeComparator());
		students.stream().forEach(System.out::println);

		System.out.println("After NameComparator Sort");
		System.out.println("ID NAME MARKS AGE\n");
		Collections.sort(students, new NameComparator());
		students.stream().forEach(System.out::println);

		System.out.println("After MarksComparator Sort");
		System.out.println("ID NAME MARKS AGE\n");
		Collections.sort(students, new MarksComparator());
		students.stream().forEach(System.out::println);		

	}
	public static List<Student1> getStudents() {
		return Arrays.asList( new Student1(1,"APPLE",10,20),new Student1(2,"BALL",9,19),new Student1(3,"CAT",8,18));
	}
}
class Student1 {
	public int Id;
	public String Fullname;
	public int Marks;
	public int Age;

	public Student1(int id, String fullname, int marks, int age) {
		this.Id = id;
		this.Fullname = fullname;
		this.Marks = marks;
		this.Age = age;
	}
	public String toString() {
		return this.Id+" "+this.Fullname+" "+this.Marks+" "+this.Age;
	}
}
class AgeComparator implements Comparator<Student1> {
	public int compare(Student1 s1, Student1 s2) {
		return s1.Age- s2.Age;
	}
}
class NameComparator implements Comparator<Student1> {
	public int compare(Student1 s1, Student1 s2) {
		return s1.Fullname.compareTo(s2.Fullname);
	}
}
class MarksComparator implements Comparator<Student1> {
	public int compare(Student1 s1, Student1 s2) {
		return s1.Marks- s2.Marks;
	}
}