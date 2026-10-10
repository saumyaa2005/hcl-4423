package collection_framework;

// // 1. Base Class
// class Animal {
//     void eat() {
//         System.out.println("Animal eats food");
//     }
// }

// // 2. Single Inheritance (Animal -> Dog)
// class Dog extends Animal {
//     void bark() {
//         System.out.println("Dog barks");
//     }
// }

// // 3. Multilevel Inheritance (Animal -> Dog -> Puppy)
// class Puppy extends Dog {
//     void weep() {
//         System.out.println("Puppy weeps");
//     }
// }

// // 4. Hierarchical Inheritance (Animal -> Cat, along with Animal -> Dog)
// class Cat extends Animal {
//     void meow() {
//         System.out.println("Cat meows");
//     }
// }

// abstract class Parent {
//     abstract void show();

//     void display() {
//         System.out.println("parent class");
//     }
// }

// class Child extends Parent {
//     @Override
//     void show() {
//         System.out.println("child class");
//     }
// }

interface Animal {
    void makesound();
}

class Dog implements Animal {
    @Override
    public void makesound() {
        System.out.println("woof woof");
    }
}

public class example {
    public static void main(String[] args) {
        // interface example
        Dog d = new Dog();
        d.makesound();

        // abstraction - override
        // Child obj = new Child();
        // obj.show();
        // obj.display();

        // System.out.println("=== 1. Single Inheritance (Dog) ===");
        // Dog d = new Dog();
        // d.eat(); // Inherited from Animal
        // d.bark(); // Dog's own method

        // System.out.println("\n=== 2. Multilevel Inheritance (Puppy) ===");
        // Puppy p = new Puppy();
        // p.eat(); // Inherited from Animal (Grandparent)
        // p.bark(); // Inherited from Dog (Parent)
        // p.weep(); // Puppy's own method

        // System.out.println("\n=== 3. Hierarchical Inheritance (Cat) ===");
        // Cat c = new Cat();
        // c.eat(); // Inherited from Animal
        // c.meow(); // Cat's own method
    }
}
