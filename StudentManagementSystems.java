import java.io.*;
import java.util.*;

public class StudentManagementSystems {
    static HashMap<Integer, String> studentMap = new HashMap<>();
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--Student Management Menu--");
            System.out.println("1. Add Student");
            System.out.println("2. Save to File");
            System.out.println("3. Load from File");
            System.out.println("4. Search by ID");
            System.out.println("5. Remove Student");
            System.out.println("6. Display All students");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    saveToFile();
                    break;
                case 3:
                    loadFromFile();
                    break;
                case 4:
                    searchStudent(sc);
                    break;
                case 5:
                    removeStudent(sc);
                    break;
                case 6:
                    displayAll();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while (choice != 0);
        sc.close();
    }

    static void addStudent(Scanner sc) {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        if (studentMap.containsKey(id)) {
            System.out.println("ID already exists!");
        } else {
            studentMap.put(id, name);
            System.out.println("Student added.");
        }
    }

    static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<Integer, String> entry : studentMap.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        studentMap.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                studentMap.put(id, name);
            }
            System.out.println("Data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        if (studentMap.containsKey(id)) {
            System.out.println("Name: " + studentMap.get(id));
        } else {
            System.out.println("Student ID not found.");
        }
    }

    static void removeStudent(Scanner sc) {
        System.out.print("Enter ID to remove: ");
        int id = sc.nextInt();
        if (studentMap.remove(id) != null) {
            System.out.println("Student removed.");
        } else {
            System.out.println("ID not found.");
        }
    }

    static void displayAll() {
        if (studentMap.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("\nStudent List:");
            for (Map.Entry<Integer, String> entry : studentMap.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue());
            }
        }
    }
}
