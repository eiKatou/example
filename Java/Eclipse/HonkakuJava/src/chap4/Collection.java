package chap4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Collection {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(5);
		list.add(3);
		list.add(4);
		list.add(1);
		System.out.println(list);

		List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		list2.add(1000);
		System.out.println(list2);

		List<Integer> list3 = new ArrayList<Integer>() {
			{
				add(1);
				add(2);
			}
		};
		list3.add(1000);
		System.out.println(list3);

		List<String> list4 = new ArrayList<>();
		Collections.addAll(list4, "bbb", "ccc");
		System.out.println(list4);

		int maxValue = Collections.max(list);
		System.out.println("max index : " + list.indexOf(maxValue));
	}

}