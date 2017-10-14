package lambda;

public class FunctionalInterfaceSample {

	public static void main(String[] args) {
		MyFunction<Integer, Integer, String> myFunc1
			= (x, y) -> String.format("myFunc1:%s,%s", x.toString(), y.toString());
		System.out.println(myFunc1.exec(101, 102));
	}

	@FunctionalInterface
	interface MyFunction<S, T, R> {
		R exec(S s, T t);
	}
}
