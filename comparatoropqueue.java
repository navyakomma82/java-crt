import java.util.PriorityQueue;
import java.util.Comparator;
class Person {
    String name;
    int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String toString() {
        return name + " (" + age + " years old)";
    }
}
public class Main {
    public static void main(String[] args) {
        PriorityQueue<Person> personQueue = new PriorityQueue<>(new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.age, p2.age); // Sort by age
            }
        });
        personQueue.add(new Person("Alice", 30));
        personQueue.add(new Person("Bob", 25));
        personQueue.add(new Person("Charlie", 35));
        personQueue.add(new Person("Diana", 28));
        System.out.println("People in order of increasing age:");
        while (!personQueue.isEmpty()) {
            System.out.println(personQueue.poll());
        }
    }
}
