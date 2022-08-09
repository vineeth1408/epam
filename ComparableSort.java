package demo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.Collections;
import java.lang.Comparable;
import java.io.*;

public class ComparableSort {
	public static void main(String[] args) {
		List<Student> students = getStudents();
		students.stream().forEach(System.out::println);
		Collections.sort(students);
		System.out.println("After Sorting");
		students.stream().forEach(System.out::println);
	}
	public static List<Student> getStudents() {
		return Arrays.asList( new Student(1,"vineeth",10,18),
							  new Student(2,"kavya",20,21),
							  new Student(3,"Naveen",21,20));
	}
}
class Student implements Comparable<Student> {
	public int Id;
	public String Fullname;
	public int Marks;
	public int Age;

	public Student(int id, String fullname, int marks, int age) {
		this.Id = id;
		this.Fullname = fullname;
		this.Marks = marks;
		this.Age = age;
	}
	public int compareTo(Student s) {
		return this.Age - s.Age; //Age > s.Age ? 1: Age < s.Age ? -1:0;

	}
	public String toString() {
		return this.Id+" "+this.Fullname+" "+this.Marks+" "+this.Age;
	}
}