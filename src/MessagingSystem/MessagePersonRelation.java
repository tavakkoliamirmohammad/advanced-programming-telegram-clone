package MessagingSystem;

public class MessagePersonRelation {
    private Messagable messagable; // every Messagable has n MessagePersonRelation
    private MessageRole role;

    public Messagable getMessagable() {
        return messagable;
    }

    public void setMessagable(Messagable messagable) {
        this.messagable = messagable;
    }

    public MessageRole getRole() {
        return role;
    }

    public void setRole(MessageRole role) {
        this.role = role;
    }
}
