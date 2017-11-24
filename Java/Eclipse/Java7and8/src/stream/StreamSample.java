package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("taro");
		list.add("hanako");
		Stream<String> stream1 = list.stream();
		stream1.forEach(System.out::println);

		String[] array = {"a1", "a2"};
		Stream<String> stream2 = Arrays.stream(array);
		stream2.forEach(System.out::println);

		Stream<String> stream3 = Stream.of("of1", "of2");
		stream3.forEach(System.out::println);

		IntStream stream4 = IntStream.range(1, 4);
		stream4.forEach(System.out::println);

		IntStream stream5 = IntStream.iterate(0, n -> n+1)
				.limit(4);
		stream5.forEach(System.out::println);

		DoubleStream stream6 = DoubleStream.generate(Math::random)
				.limit(4);
		stream6.forEach(System.out::println);

		IntStream stream7 = IntStream.range(100, 104);
		IntStream stream8 = IntStream.iterate(20, n -> n+1).limit(4);
		IntStream stream9 = IntStream.concat(stream7, stream8);
		stream9.forEach(System.out::println);


	}

}
