package collection_framework;

import java.util.*;

public class hashmapp {
    public static void main(String[] args) {
        Map<Integer, String> mp = new HashMap<>();
        mp.put(101, "Saumya");
        mp.put(102, "Prakhar");
        mp.put(103, "Riya");

        System.out.println(mp);

        mp.remove(103);
        System.out.println(mp);

        mp.putIfAbsent(103, "Riya");
        System.out.println(mp);

    }
}
