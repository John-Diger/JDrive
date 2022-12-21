package src;

import java.io.Serializable;

public class RequestForm implements Serializable {
	Method method;
	Object data;

	public RequestForm(Method method) {
		this.method = method;
	}

	public RequestForm(Method method, Object data) {
		this.method = method;
		this.data = data;
	}

	public Method getMethod() {
		return method;
	}

	public Object getData() {
		return data;
	}
}
