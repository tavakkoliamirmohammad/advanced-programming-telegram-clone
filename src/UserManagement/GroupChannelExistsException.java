package UserManagement;

public class GroupChannelExistsException extends Exception{
    public GroupChannelExistsException() {
        super();
    }

    public GroupChannelExistsException(String message) {
        super(message);
    }
}
