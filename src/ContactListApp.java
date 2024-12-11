
import java.util.List;
import java.util.Scanner;

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
