package fu.talenthub.job.exception;

public class JobInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JobInvalidException(String message) {
		super(message);
	}
}
