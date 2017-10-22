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
		System.out.println("toString:" + status2.toString());
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

		System.out.println();
		System.out.println("getPrintCode:" + Status3.SUCCESS.getPrintCode());
		System.out.println("getPrintCode:" + Status3.ERROR.getPrintCode());
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

enum Status3 {
	SUCCESS(0) {
		@Override
		public String getPrintCode() {
			return "<p class='ok'>" + getCode() + "</p>";
		}
	}, ERROR(1) {
		@Override
		public String getPrintCode() {
			return "<p class='error'>" + getCode() + "</p>";
		}
	};
	private final int code;

	private Status3(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	abstract public String getPrintCode();
}
