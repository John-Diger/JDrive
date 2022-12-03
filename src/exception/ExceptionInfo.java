package exception;

public enum ExceptionInfo {
	;
	private String message;

	ExceptionInfo(String message) {
		this.message = message;
	}

	public String read() {
		return this.message;
	}
}
