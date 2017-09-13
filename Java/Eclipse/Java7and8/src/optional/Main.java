package optional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		System.out.println("sample1");
		sample1();
		System.out.println("\nsample2");
		sample2();
		System.out.println("\nsample3");
		sample3();
		System.out.println("\nsample4");
		sample4();
	}

	public static void sample1() {
//		int[] numbers = {1, 2, 3};
		int[] numbers = {};
		OptionalDouble aveOption = Arrays.stream(numbers).average();
		double average = aveOption.orElse(0);
		if (aveOption.isPresent()) {
			System.out.println("値が存在します。");
		} else {
			System.out.println("値が存在しません。");
		}
		System.out.println("average = " + average);
	}

	public static void sample2() {
		Optional<String> op = Optional.empty();
		// 値が存在する場合のみ出力する
		op.ifPresent(s -> System.out.println(s));

		Optional<String> op2 = Optional.ofNullable("ofNullable method.");
		// 値が存在する場合のみ出力する
		op2.ifPresent(s -> System.out.println(s));

		Optional<String> op3 = Optional.ofNullable(null);
		// 値が存在する場合のみ出力する
		op3.ifPresent(s -> System.out.println(s));

		// nullを入れるとヌルポ
//		Optional<String> op4 = Optional.of(null);
		Optional<String> op4 = Optional.of("of method.");
		// 値が存在する場合のみ出力する
		op4.ifPresent(s -> System.out.println(s));
	}

	public static void sample3() {
		Stream<String> stream = Stream.of("xxa", "baabb", "cab", "aab");
		stream.filter(s -> s.contains("aa"))
			.findFirst()
			.ifPresent(s -> System.out.println(s));
	}

	public static void sample4() {
		Stream<String> stream = Stream.of("xxa", "baabb", "cab", "aab");
		stream.max(Comparator.naturalOrder())
			// filterで、yから始まる文字列だった時のみ出力（xxaなのでyから始まらない）
//			.filter(s -> s.startsWith("y"))
			.filter(s -> s.startsWith("x"))
			.ifPresent(s -> System.out.println(s));
	}
}
