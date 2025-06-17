import java.util.*;
class Person {
    String name;
    int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String toString() {
        return name + " (" + age + ")";
    }
}
public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("Diana", 28));
        people.sort((p1, p2) -> Integer.compare(p1.age, p2.age));
        System.out.println("Sorted by age:");
        people.forEach(person -> System.out.println(person));
        people.sort((p1, p2) -> p1.name.compareTo(p2.name));
        System.out.println("\nSorted by name:");
        people.forEach(System.out::println);
    }
}
