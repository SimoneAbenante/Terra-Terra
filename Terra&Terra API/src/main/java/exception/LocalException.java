package exception;

public class LocalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9013604543246557084L;

	private String message = "Generic Exception from T&T application";

	public LocalException() {
		super();
	}

	public LocalException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return message + "\n" + super.toString();
	}

}
