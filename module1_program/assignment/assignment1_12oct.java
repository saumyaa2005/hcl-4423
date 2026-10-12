package assignment;

import java.util.*;

//create an arraylist of employee type and apply crud operation on the employee object use iterator and list iterator both to traverse the employees apart from these we need to iterate these employees on the decreasing and increasing order

//Employee class
class Employee implements Comparable<Employee> {
    int id;
    String name;
    double salary;

    // constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // sorting
    @Override
    public int compareTo(Employee other) {
        return this.id - other.id;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}

public class assignment1_12oct {

    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();

        // CRUD operation
        empList.add(new Employee(104, "Rahul", 65000.0));
        empList.add(new Employee(101, "Saumya", 85000));
        empList.add(new Employee(103, "Aman", 45000));
        empList.add(new Employee(102, "Pooja", 72000));

        System.out.println("Employees Added Successfully (Create)");

        System.out.println("Reading all employees: ");
        for (Employee emp : empList) {
            System.out.println(emp);
        }

        System.out.println("Updating employee with ID 102");
        for (Employee emp : empList) {
            if (emp.getId() == 102) {
                emp.setSalary(55000);
                emp.setName("Riya");

                System.out.println("Record updated sucessfully");
                break;
            }
        }

        System.out.println("Deleting employee with ID 103");
        empList.removeIf(emp -> emp.getId() == 103);
        System.out.println("Employee 103 removed. Remaining count: " + empList.size());

        // Iterator traverse
        System.out.println("\nTraversal Using Iterator (Forward)");
        Iterator<Employee> itr = empList.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        // List iterator
        System.out.println("\nTraversal Using ListIterator (Forward) ");
        ListIterator<Employee> litr = empList.listIterator();
        while (litr.hasNext()) {
            System.out.println(litr.next());
        }
        System.out.println("\nTraversal Using ListIterator (Backward)");
        while (litr.hasPrevious()) {
            System.out.println(litr.previous());
        }

        // iterate in increasing and decreasing order
        Collections.sort(empList);
        System.out.println("\n Employees in Increasing Order (by ID) ");
        for (Employee emp : empList) {
            System.out.println(emp);
        }

        Collections.sort(empList, Collections.reverseOrder());
        System.out.println("\n--- Employees in Decreasing Order (by ID) ---");
        for (Employee emp : empList) {
            System.out.println(emp);
        }

        Collections.sort(empList, (e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\n--- Employees in Decreasing Order (by Salary) ---");
        for (Employee emp : empList) {
            System.out.println(emp);
        }
    }
}