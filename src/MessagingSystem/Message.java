package MessagingSystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Message {

    private static List<Message> messages = new ArrayList<>();
    private Calendar calendar;
    private MessageType messageType;
    private List<MessagePersonRelation> messagePersonRelations = new ArrayList<>();
    private MessageData messageData; // every message data can be assign to n message.
    private Message relatedMessage; // every related message has n message

    public Message getRelatedMessage() {
        return relatedMessage;
    }

    public void setRelatedMessage(Message relatedMessage) {
        this.relatedMessage = relatedMessage;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageData getMessageData() {
        return messageData;
    }

    public void setMessageData(MessageData messageData) {
        this.messageData = messageData;
    }

    public List<MessagePersonRelation> getMessagePersonRelations() {
        return messagePersonRelations;
    }

    public void setMessagePersonRelations(List<MessagePersonRelation> messagePersonRelations) {
        this.messagePersonRelations = messagePersonRelations;
    }

    public MessageRole getMessagbleRole(Messagable messagable) {
        for (MessagePersonRelation messagePersonRelation : messagePersonRelations) {
            if (messagePersonRelation.getMessagable() == messagable) {
                return messagePersonRelation.getRole();
            }
        }
        return null;
    }

    public List<Messagable> getSenders() {
        List<Messagable> messagables = new ArrayList<>();
        for (MessagePersonRelation messagePersonRelation : messagePersonRelations) {
            if (messagePersonRelation.getRole() == MessageRole.Sender) {
                messagables.add(messagePersonRelation.getMessagable());
            }
        }
        return messagables;
    }

    public List<Messagable> getReceiver() {
        List<Messagable> messagables = new ArrayList<>();
        for (MessagePersonRelation messagePersonRelation : messagePersonRelations) {
            if (messagePersonRelation.getRole() == MessageRole.Receiver) {
                messagables.add(messagePersonRelation.getMessagable());
            }
        }
        return messagables;
    }
    public static List<Message> getMessagbleMessages(Messagable mess){
        List<Message> myMessages = new ArrayList<>();
        for(Message message : messages){
            List<Messagable> messagables = message.getReceiver();
            for(Messagable messagable : messagables){
                if(messagable == mess){
                    myMessages.add(message);
                    break;
                }
            }
        }
        return myMessages;
    }

    public void sendMessage() {
        messages.add(this);
    }
}
