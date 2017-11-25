package stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.tuple.Pair;

public class PairSample {

	public static void main(String[] args) throws Exception {
		Path path = Paths.get("/Users/ei/Documents/GitHub/Sample/Java/Eclipse/Java7and8/src/stream/RFC_HTTP.txt");
		try (Stream<String> lines = Files.lines(path)) {
			Stream<Integer> lineNums = IntStream.iterate(1, n -> n+1).boxed();
			zip(lineNums, lines)
				.parallel()
				.filter(p -> p.getRight().contains("HTTP"))
				.forEach(p -> System.out.println(p.getLeft() + "行目：" + p.getRight()));
		}
	}

	public static <T, U> Stream<Pair<T, U>> zip(Stream<T> f, Stream<U> s) {
		Iterator<T> fite = f.iterator();
		Iterator<U> site = s.iterator();
		Iterator<Pair<T, U>> iterator = new Iterator<Pair<T, U>>() {
			@Override
			public boolean hasNext() {
				return fite.hasNext() && site.hasNext();
			}

			@Override
			public Pair<T, U> next() {
				return Pair.of(fite.next(), site.next());
			}
		};

		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iterator, Spliterator.NONNULL | Spliterator.ORDERED), false);
	}

}
