package OOPs;

class A { // super
    A() {
        System.out.println(" Parent A");
    }
}

class B extends A { // this
    B() {
        super(); // call parent A constructor first
        System.out.println(" child B");
    }
}

public class Demo {

    // Demo(){
    // System.out.println(" i am constructor");
    // }
    public static void main(String[] args) {
        new B();
    }
}
