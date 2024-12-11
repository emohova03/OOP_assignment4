import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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