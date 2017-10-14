package lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class FaunctionSample {

	public static void main(String[] args) {
		// 通常の匿名クラスの記述
		Function<Integer, String> func1 = new Function<Integer, String>() {
			@Override
			public String apply(Integer t) {
				return "func1:" + t.toString();
			}
		};
		System.out.println(func1.apply(101));

		// Lambda式での記述
		Function<Integer, String> func2 = (Integer x) -> {
			return "func2:" + x.toString();
		};
		System.out.println(func2.apply(102));

		// 型推論でIntegerを省略可能
		Function<Integer, String> func3 = (x) -> {
			return "func3:" + x.toString();
		};
		System.out.println(func3.apply(103));

		// 引数が1つの時は丸カッコを省略可能
		Function<Integer, String> func4 = x -> {
			return "func4:" + x.toString();
		};
		System.out.println(func4.apply(104));

		// 実行文が1つの時は波カッコを省略可能
		Function<Integer, String> func5 = x -> "func5:" + (x).toString();
		System.out.println(func5.apply(105));

		// andThenで合成関数を作る
		Function<Integer, Integer> funcPlus = x -> x+100;
		Function<Integer, String> funcPrint = x -> "funcPrint:" + (x).toString();
		Function<Integer, String> funcPlusPrint = funcPlus.andThen(funcPrint);
		System.out.println(funcPlusPrint.apply(500));

		// composeで合成関数を作る
		System.out.println(funcPrint.compose(funcPlus).apply(600));

		// Supplierを利用する
		Supplier<String> func6 = () -> "func6:test";
		System.out.println(func6.get());

		// BiFunctionを利用する
		BiFunction<Integer, Integer, String> func7
			= (x, y) -> "func7:" + String.valueOf(x.intValue() + y.intValue());
		System.out.println(func7.apply(5, 13));

		// プリミティブ型で利用できるIntFunctionを利用する
		IntFunction<String> func8
			= (x) -> "func8:" + String.valueOf(x);
		System.out.println(func8.apply(13));

	}
}
