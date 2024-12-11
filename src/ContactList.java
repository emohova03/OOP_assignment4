import java.util.ArrayList;
import java.util.List;

class ContactList {
    private List<Person> contacts = new ArrayList<>();

    public void addPerson(Person person) {
        contacts.add(person);
    }

    public void printAllContacts() {
        for (Person person : contacts) {
            System.out.println(person);
        }
    }

    public List<Person> searchByName(String name) {
        List<Person> result = new ArrayList<>();
        for (Person person : contacts) {
            if (person.getName().equalsIgnoreCase(name)) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> searchByEmail(String email) {
        List<Person> result = new ArrayList<>();
        for (Person person : contacts) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> searchByPhone(String phone) {
        List<Person> result = new ArrayList<>();
        for (Person person : contacts) {
            if (person.getPhone().equals(phone)) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
    }
}

