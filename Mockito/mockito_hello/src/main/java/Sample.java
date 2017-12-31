public class Sample {
    private Person person;

    public static void main(String[] args) {
        Sample sample = new Sample(new Person("yamada", "masato", 15));
        System.out.println(sample.greet());
    }

    public String greet() {
        return "Hello, " + this.person.name() + "!!";
    }

    public String say() {
        return person.say("Hey!, ");
    }

    public void tweet() {
        person.tweet("Tweet, ");
    }

    public Sample(Person person) {
        this.person = person;
    }
}
