package collection_framework;

import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int rollno;
    int age;
    double marks;

    public Student(String name, int rollno, int age, double marks) {
        this.name = name;
        this.rollno = rollno;
        this.age = age;
        this.marks = marks;
    }

    // Comparable ka compareTo method
    @Override
    public int compareTo(Student other) {
        // Roll number ke basis par sort (Ascending order)
        return this.rollno - other.rollno;
    }

    // Student object ko readable print karne ke liye
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollno=" + rollno +
                ", age=" + age +
                ", marks=" + marks +
                '}';
    }
}

public class task1 {

    public static void main(String[] args) {
        // List of Student objects
        List<Student> ls = new ArrayList<>();
        ls.add(new Student("saumya", 52, 20, 100));
        ls.add(new Student("prakhar", 53, 21, 100));
        ls.add(new Student("riya", 12, 19, 88));
        ls.add(new Student("raghav", 35, 22, 92));

        System.out.println("--- Before Sorting ---");
        for (Student s : ls) {
            System.out.println(s);
        }

        // Sorting using Comparable (rollno wise)
        Collections.sort(ls);

        System.out.println("\n--- After Sorting (by rollno) ---");
        for (Student s : ls) {
            System.out.println(s);
        }
    }
}
