package nio2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

public class FilesAndPath {
	static final String DOWNLOAD_PATH = "/Users/ei/Downloads/";

	public static void main(String[] args) throws IOException {
		Path path1 = Paths.get("foo.txt");
		System.out.println("path1 : " + path1);

		Path path2 = Paths.get(DOWNLOAD_PATH);
		// 親パスの取得
		Path path3 = path2.getParent();
		// ルートの取得
		Path path4 = path2.getRoot();
		// 2つのPathを組み合わせて、Pathを生成
		Path path5 = path2.resolve(path1);
		System.out.println("path2 : " + path2);
		System.out.println("path3 : " + path3);
		System.out.println("path4 : " + path4);
		System.out.println("path5 : " + path5);
		// ファイル名のみを取得
		System.out.println(path5.getFileName());
		System.out.println();


		// ファイルがあるか調べる。ファイルがなければファイルを作成。
		Path basePath = Paths.get(DOWNLOAD_PATH);
		Path fooPath = basePath.resolve(Paths.get("foo.txt"));
		System.out.println(fooPath);
		boolean fooExists = Files.exists(fooPath, LinkOption.NOFOLLOW_LINKS);
		if (fooExists) {
			System.out.println("foo.txt is exist.");
		} else {
			System.out.println("foo.txt is not exist.");
			// ファイルを作成
			Files.createFile(fooPath);
		}

		// ディレクトリがあるか調べる。ディレクトリがなければ、ディレクトリを作成。
		Path tmpPath = basePath.resolve(Paths.get("tmp"));
		System.out.println(tmpPath);
		if (Files.exists(tmpPath, LinkOption.NOFOLLOW_LINKS)) {
			System.out.println("tmp dir is exist.");
		} else {
			System.out.println("tmp dir is not exist.");
			// ディレクトリを作成
			Files.createDirectory(tmpPath);
		}

		// ファイルのコピー
		Path hogePath = tmpPath.resolve(Paths.get("hoge.txt"));
		Files.copy(fooPath, hogePath);

		// ファイルの移動と名前の変更
		Path foo2Path = basePath.resolve(Paths.get("foo2.txt"));
		Files.move(fooPath, foo2Path);

		Path tmp2Path = basePath.resolve(Paths.get("tmp2"));
		Files.move(tmpPath, tmp2Path);
		Files.move(tmp2Path, tmpPath);

		// ディレクトリ配下を走査して、一覧を出力する
		System.out.println("\n== Files.list ==");
		Files.list(basePath)
			.forEach(System.out::println);

		// ディレクトリ配下を走査して、一覧を出力する
		System.out.println("\n== Files.walk ==");
		Files.walk(basePath)
			.forEach(System.out::println);

		// ディレクトリ配下を走査して、一覧を出力する
		System.out.println("\n== Files.walkFileTree ==");
		FileVisitor<Path> visitor = new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("preVisitDirectory:" + dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println("visitFile:" + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.TERMINATE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree(basePath, visitor);

		// ファイルの属性情報を取得
		System.out.println("\n== Files.readAttributes ==");
		PosixFileAttributes attributes = Files.readAttributes(foo2Path, PosixFileAttributes.class);
		System.out.println("Size:" + attributes.size());
		System.out.println("Owner:" + attributes.owner());
		System.out.println("Permission:" + PosixFilePermissions.toString(attributes.permissions()));

		// ファイルの削除
		System.out.println("\n== 後始末 ==");
		Files.deleteIfExists(fooPath);
		Files.deleteIfExists(foo2Path);
//		Files.deleteIfExists(hogePath);
		Files.walk(tmpPath).forEach(path -> {
			if (!Files.isDirectory(path)) {
				try {
					System.out.println(path);
					Files.deleteIfExists(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			});
		Files.deleteIfExists(tmpPath);
	}

}
