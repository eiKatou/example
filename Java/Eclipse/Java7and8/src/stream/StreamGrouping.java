package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGrouping {

	public static void main(String[] args) {
		List<Person> groupA = new ArrayList<>();
		groupA.add(new Person("yamato", 20, "Osaka"));
		groupA.add(new Person("sasuke", 20, "Tokyo"));
		groupA.add(new Person("sakura", 20, "Nagoya"));
		groupA.add(new Person("minato", 20, "Osaka"));
		groupA.add(new Person("naruto", 20, "Osaka"));
		groupA.add(new Person("hinata", 20, "Nagoya"));
		groupA.add(new Person("tyouji", 20, "Tokyo"));
		groupA.add(new Person("kakasi", 20, "Tokyo"));

		Map<String, List<Person>> personGroupAByCity = groupA.stream()
				.collect(Collectors.groupingBy(p -> p.city));
		System.out.println(personGroupAByCity.toString());

		List<Person> groupB = new ArrayList<>();
		groupB.add(new Person("asuma", 40, "Osaka"));
		groupB.add(new Person("sikamaru", 20, "Tokyo"));
		groupB.add(new Person("ino", 20, "Nagoya"));
		groupB.add(new Person("kurenai", 40, "Osaka"));
		groupB.add(new Person("kiba", 20, "Osaka"));
		groupB.add(new Person("akamaru", 2, "Nagoya"));
		groupB.add(new Person("shino", 2, "Nagoya"));
		groupB.add(new Person("gai", 20, "Nagoya"));

		Map<Object, Map<Object, List<Person>>> personGroupBByCity = groupB.stream()
				.collect(Collectors.groupingBy(p -> p.city, Collectors.groupingBy(p -> p.age)));
		System.out.println(personGroupBByCity.toString());

//		実行結果
//		{Nagoya=[Person [sakura,20,Nagoya], Person [hinata,20,Nagoya]], Tokyo=[Person [sasuke,20,Tokyo], Person [tyouji,20,Tokyo], Person [kakasi,20,Tokyo]], Osaka=[Person [yamato,20,Osaka], Person [minato,20,Osaka], Person [naruto,20,Osaka]]}
//		{Nagoya={2=[Person [akamaru,2,Nagoya], Person [shino,2,Nagoya]], 20=[Person [ino,20,Nagoya], Person [gai,20,Nagoya]]}, Tokyo={20=[Person [sikamaru,20,Tokyo]]}, Osaka={20=[Person [kiba,20,Osaka]], 40=[Person [asuma,40,Osaka], Person [kurenai,40,Osaka]]}}
	}

}

class Person {
	String name;
	int age;
	String city;

	Person(String name, int age, String city) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Person [" + name + "," + age + "," + city + "]";
	}
}
