package exception;

public class BaseException extends RuntimeException {
	private ExceptionInfo exceptionInfo;

	public BaseException(ExceptionInfo exceptionInfo) {
		super(exceptionInfo.read());
		this.exceptionInfo = exceptionInfo;
	}
}
