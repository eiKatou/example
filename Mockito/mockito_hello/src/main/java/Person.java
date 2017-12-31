public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String name() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public String say(String hello) {
        return String.format("%s say %s", this.name(), hello);
    }
}
