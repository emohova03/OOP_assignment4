class Person {
    private String name;
    private String email;
    private String phone;
    private String address;
    private int age;

    public Person(String name, String email, String phone, String address, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address + ", Age: " + age;
    }
}
