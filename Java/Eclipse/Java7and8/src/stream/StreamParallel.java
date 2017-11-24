package stream;

import java.io.IOException;
import java.util.stream.IntStream;

public class StreamParallel {
	public static void main(String[] args) throws IOException {
		IntStream.range(0, 100)
			.forEach(System.out::println);
		System.out.println("==================");

		IntStream.range(0, 100)
			.parallel()
			.forEach(System.out::println);

//		Path path = Paths.get("/Users/ei/Documents/GitHub/Sample/Java/Eclipse/Java7and8/src/stream/RFC_HTTP.txt");
//		try (Stream<String> lines = Files.lines(path)) {
//			lines.parallel().filter(line -> line.contains("HTTP"))
//				.forEach(System.out::println);
//		}
	}
}
