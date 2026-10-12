package interfacee;

@FunctionalInterface
interface calculate {
    void add(int a, int b);
}

public class intefacee {
    public static void main(String[] args) {
        calculate c = (a, b) -> System.out.println(a + b);
        c.add(10, 20);
    }

}