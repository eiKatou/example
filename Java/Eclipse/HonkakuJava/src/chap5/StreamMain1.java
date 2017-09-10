package chap5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamMain1 {

	public static void main(String[] args) {
		Stream<String> names = Arrays.asList("taro", "hanako", "minato").stream();
		names.forEach(System.out::println);
		System.out.println();

		Stream<String> names2 = Arrays.asList("taro", "hanako", "minato").stream();
		names2.forEach(s -> System.out.println("name is " + s));
		System.out.println();

		List<Student> studens = new ArrayList<>();
		studens.add(new Student("Takashi", 29));
		studens.add(new Student("Hiroaki", 30));
		studens.add(new Student("Kana", 31));
		studens.add(new Student("Kimiko", 32));

		System.out.println("== All ==");
		studens.forEach(System.out::println);

		System.out.println();
		System.out.println("== filter i ==");
		studens.stream()
			.filter(s -> s.getName().contains("i"))
			.forEach(System.out::println);

		System.out.println();
		System.out.println("== filter 30Over ==");
		studens.stream()
			.filter(s -> s.is30Over())
			.forEach(System.out::println);
	}

}

class Student {
	private String name;
	private int age;

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public boolean is30Over () {
		return age >= 30;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

}