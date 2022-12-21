package src;

public enum Method {
	UPLOAD("u"), DOWNLOAD("d");

	private String command;

	Method(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}
}
