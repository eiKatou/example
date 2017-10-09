package nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

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

		// TODO:ディレクトリ配下を走査して、一覧を出力する

		// TODO:ファイルの属性情報を取得

		// ファイルの削除
//		Files.deleteIfExists(fooPath);
//		Files.deleteIfExists(foo2Path);
//		Files.deleteIfExists(hogePath);
//		Files.deleteIfExists(tmpPath);
	}

}
