package UserManagement;

public class InvalidUserAuthenticationException extends Exception {
    public InvalidUserAuthenticationException() {
        super();
    }

    public InvalidUserAuthenticationException(String message) {
        super(message);
    }
}
