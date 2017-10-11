package enumeratedtype;

public class Main {

	public static void main(String[] args) {
		Status status = Status.SUCCESS;
		switch (status) {
		case SUCCESS:
			System.out.println("SUCCESS");
			break;
		case ERROR:
			System.out.println("ERROR");
			break;
		default:
			break;
		}

		Status2 status2 = Status2.ERROR;
		System.out.println("name:" + status2.name());
		System.out.println("code:" + status2.getCode());
		switch (status2) {
		case SUCCESS:
			System.out.println("SUCCESS");
			break;
		case ERROR:
			System.out.println("ERROR");
			break;
		default:
			break;
		}
	}

}

enum Status {
	SUCCESS, ERROR
}

enum Status2 {
	SUCCESS(0), ERROR(1);
	private final int code;

	private Status2(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
