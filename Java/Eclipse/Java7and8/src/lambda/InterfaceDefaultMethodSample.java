package lambda;

import java.time.LocalTime;

public class InterfaceDefaultMethodSample {
	public static void main(String[] args) {
		MyInterface<Integer, Integer, String> myInterface = new MyInterface<Integer, Integer, String>() {
			@Override
			public String exec(Integer s, Integer t) {
				return "";
			}
		};
		System.out.println(myInterface.getNowTime());
		System.out.println(MyInterface.getGreeting());
	}

	interface MyInterface<S, T, R> {
		R exec(S s, T t);

		default String getNowTime() {
			return LocalTime.now().toString() + " " + getGreeting();
		}

		public static String getGreeting() {
			return "Hello";
		}
	}
}
