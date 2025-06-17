import java.util.HashMap;
import java.util.Map;
public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> studentMarks = new HashMap<>();
        studentMarks.put("Alice", 85);
        studentMarks.put("Bob", 92);
        studentMarks.put("Charlie", 78);
        studentMarks.put("Diana", 90);
        System.out.println("Student Marks: " + studentMarks);
        System.out.println("Marks of Bob: " + studentMarks.get("Bob"));
        if (studentMarks.containsKey("Alice")) {
            System.out.println("Alice is in the map.");
        }
        if (studentMarks.containsValue(90)) {
            System.out.println("Someone scored 90.");
        }
        System.out.println("\nAll students and their marks:");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        studentMarks.remove("Charlie");
        System.out.println("\nAfter removing Charlie: " + studentMarks);
    }
}
