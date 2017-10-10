package nio2;

import static java.nio.file.StandardOpenOption.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 簡単なファイルの書き込みと読み込み
 *
 * @author ei
 */
public class SimpleReadWrite {
	static final String DOWNLOAD_PATH = "/Users/ei/Downloads/";

	public static void main(String[] args) throws IOException {
		Path basePath = Paths.get(DOWNLOAD_PATH);
		Path fooPath = basePath.resolve(Paths.get("foo.txt"));

		// ファイルがすでにあれば消しておく
		System.out.println("\n== Files.deleteIfExists ==");
		Files.deleteIfExists(fooPath);
		System.out.println("File deleted.");

		// ファイルの書き込み
		System.out.println("\n== Files.newBufferedWriter ==");
		try (BufferedWriter writer = Files.newBufferedWriter(fooPath)) {
			writer.append("abc");
			writer.newLine();
			writer.append("def");
			writer.newLine();
		}

		// ファイルの読み込み
		System.out.println("\n== Files.newBufferedReader ==");
		try (BufferedReader reader = Files.newBufferedReader(fooPath)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}

		// ファイルの書き込み
		System.out.println("\n== Files.write ==");
		List<String> lines = Stream.of("123", "ccc").collect(Collectors.toList());
		Files.write(fooPath, lines, CREATE, WRITE, APPEND);

		// ファイルの読み込み
		System.out.println("\n== Files.readAllLines ==");
		Files.readAllLines(fooPath).forEach(System.out::println);

		// ファイルの読み込み
		System.out.println("\n== Files.lines ==");
		Files.lines(fooPath).forEach(System.out::println);
	}

}
