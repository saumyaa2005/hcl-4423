package example;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

//collectionn interface(dynamic in size)- list(dynamic size, duplicates allowed, want to preserve insertion order), 
//        -set(duplicat not allowed, insertion order is random)
//       - queue(fifo, sequence)
public class Collectionn {
    public static void main(String arg[]) {
        // 1. ArrayList
        System.out.println("1. ArrayList ");
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Apple");

        System.out.println("Fruits List: " + fruits);
        System.out.println("List size: " + fruits.size());
        System.out.println("Get element at index 1: " + fruits.get(1));
        fruits.remove("Apple");
        System.out.println("After removing Apple: " + fruits);

        // 2. LinkedHashSet
        System.out.println("\n2. Linked Hashset ");
        LinkedHashSet<String> colors = new LinkedHashSet<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add("Red"); // duplicate will not be added

        System.out.println("Colors Set: " + colors);
        System.out.println("Is Red present: " + colors.contains("Red"));
        colors.remove("Blue");
        System.out.println("After removing Blue: " + colors);

        // 3. Queue (LinkedList)
        System.out.println("\n3. Queue ");
        Queue<String> line = new LinkedList<>();
        line.add("Person 1");
        line.add("Person 2");
        line.add("Person 3");

        System.out.println("Queue elements: " + line);
        System.out.println("Front element (peek): " + line.peek());
        System.out.println("Removed from front (poll): " + line.poll());
        System.out.println("Queue after poll: " + line);

        // 4. Map (HashMap - Key Value pairs)
        System.out.println("\n4. HashMap ");
        HashMap<Integer, String> student = new HashMap<>();
        student.put(101, "Saumya");
        student.put(102, "Amit");
        student.put(103, "Pooja");

        System.out.println("Students Map: " + student);
        System.out.println("Value of key 101: " + student.get(101));
        System.out.println("Contains key 102: " + student.containsKey(102));
        student.remove(103);
        System.out.println("After removing key 103: " + student);
    }
}
