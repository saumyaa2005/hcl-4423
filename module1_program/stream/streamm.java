package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class streamm {
    public static void main(String[] args) {
        /*
         * // --- Purana Code (Commented) ---
         * ArrayList<Integer> l1 = new ArrayList<>();
         * l1.add(10);
         * l1.add(15);
         * l1.add(20);
         * l1.add(25);
         * l1.add(30);
         * 
         * ArrayList<Integer> l2 = new ArrayList<>();
         * l2.add(1);
         * l2.add(2);
         * l2.add(3);
         * 
         * Stream<Integer> stream1 = l1.stream();
         * long totalCount1 = stream1.count();
         * System.out.println("l1 total elements: " + totalCount1);
         * 
         * long totalCount2 = l2.stream().count();
         * System.out.println("l2 total elements: " + totalCount2);
         */

        // --- Method Reference Example ---
        List<String> names = Arrays.asList("amit", "rahul", "neha");

        System.out.println("Uppercase Names:");
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(10, 20, 30);
        System.out.println("Numbers as Strings:");
        numbers.stream()
                .map(String::valueOf)
                .forEach(System.out::println);
    }
}
