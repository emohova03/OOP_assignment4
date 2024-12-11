import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ContactListPersistentService class
class ContactListPersistentService {
    private static final String DB_URL = "jdbc:sqlite:contacts.db";

    public ContactListPersistentService() {
        createDatabase();
    }

    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Contacts (" +
                    "Name TEXT, Email TEXT, Phone TEXT, Address TEXT, Age INTEGER);";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
        }
    }

    public void saveContacts(List<Person> contacts) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Contacts (Name, Email, Phone, Address, Age) VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (Person person : contacts) {
                    pstmt.setString(1, person.getName());
                    pstmt.setString(2, person.getEmail());
                    pstmt.setString(3, person.getPhone());
                    pstmt.setString(4, person.getAddress());
                    pstmt.setInt(5, person.getAge());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }

    public List<Person> loadContacts() {
        List<Person> contacts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Contacts;");
            while (rs.next()) {
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                int age = rs.getInt("Age");
                contacts.add(new Person(name, email, phone, address, age));
            }
        } catch (SQLException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
        }
        return contacts;
    }
}

// Main class
public class ContactListApp {
    public static void main(String[] args) {
        ContactList contactList = new ContactList();
        ContactListPersistentService service = new ContactListPersistentService();

        contactList.setContacts(service.loadContacts());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Contact\n2. Print All Contacts\n3. Search by Name\n4. Save Contacts\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    contactList.addPerson(new Person(name, email, phone, address, age));
                    break;
                case 2:
                    contactList.printAllContacts();
                    break;
                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    List<Person> results = contactList.searchByName(searchName);
                    results.forEach(System.out::println);
                    break;
                case 4:
                    service.saveContacts(contactList.getContacts());
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
