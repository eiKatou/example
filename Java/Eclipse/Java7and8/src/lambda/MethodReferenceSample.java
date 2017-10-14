package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;

public class MethodReferenceSample {

	public static void main(String[] args) {
		// クラスメソッド参照
		Function<Integer, String> func1 = String::valueOf;
		System.out.println(func1.apply(20));

		// インスタンスメソッド参照
		List<String> list = new ArrayList<>();
		Consumer<String> addListFunc1 = list::add;
		addListFunc1.accept("aaa");
		addListFunc1.accept("bbb");
		list.forEach(System.out::println);

		// 配列を生成するラムダ式
		IntFunction<String[]> arrayFunc1 = (size) -> new String[size];
		String[] array1 = arrayFunc1.apply(5);
		System.out.println("Array1.length " + array1.length);

		// コンストラクタ参照
		IntFunction<String[]> arrayFunc2 = String[]::new;
		String[] array2 = arrayFunc2.apply(13);
		System.out.println("Array2.length " + array2.length);
	}

}
