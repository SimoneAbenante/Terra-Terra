package exception;

public class LocalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9013604543246557084L;

	private static final String n = "\n";

	private String message = "Exception from T&T application:\n";

	public LocalException() {
		super();
	}

	public LocalException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return message + n + super.toString();
	}

	public void setMessage(String message) {
		this.message += message + n;
	}

	public void setMessage(String message, String classes, String method) {
		this.message += message + n + "In: " + classes + n + "On: " + method;
	}

}
