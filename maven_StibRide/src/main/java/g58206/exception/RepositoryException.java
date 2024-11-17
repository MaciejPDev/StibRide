package g58206.exception;

public class RepositoryException extends Exception {

    public RepositoryException(String msg) {
        super(msg);
    }

    public RepositoryException(Exception exception) {
        super(exception);
    }
}
