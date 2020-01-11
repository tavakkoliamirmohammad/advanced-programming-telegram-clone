package MessageViewPages;

import DataManagement.*;
import MessagingSystem.Message;
import MessagingSystem.MessagePersonRelation;
import MessagingSystem.MessageRole;
import MessagingSystem.MessageType;
import UserManagement.*;

import java.util.Calendar;
import java.util.Scanner;

public class MessageMainView {

    private User user;
    private Scanner scanner = new Scanner(System.in);
    private Message message = new Message();

    public MessageMainView(User user) {
        this.user = user;
    }

    private void showMenu() {
        System.out.println("1. add receivers");
        System.out.println("2. add data to message(text of upload file)");
        System.out.println("3. send");
        System.out.println("4. exit");
    }

    private void addReceiver() {
        System.out.println("Where do you want to send? ");
        System.out.println("1. user");
        System.out.println("2. group");
        System.out.println("3. channel");
        System.out.println("4. exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 4) {
            return;
        }
        if (choice == 1) {
            System.out.println("Enter the id");
            String id = scanner.nextLine();
            try {
                User u = User.findById(id);
                MessageRole messageRole = message.getMessagbleRole(u);
                if (messageRole == null) {
                    System.out.println("User is already in receiver list");
                } else {
                    MessagePersonRelation messagePersonRelation = new MessagePersonRelation();
                    messagePersonRelation.setRole(MessageRole.Receiver);
                    messagePersonRelation.setMessagable(u);
                    message.getMessagePersonRelations().add(messagePersonRelation);
                }

            } catch (InvalidUserAuthenticationException e) {
                System.out.println(e.getMessage());
            }
        } else if (choice == 2) {
            System.out.println("Enter the id");
            String id = scanner.nextLine();
            try {
                Group group = Group.findGroupById(id);
                MessageRole messageRole = message.getMessagbleRole(group);
                if (messageRole == null) {
                    System.out.println("User is already in receiver list");
                } else {
                    MessagePersonRelation messagePersonRelation = new MessagePersonRelation();
                    messagePersonRelation.setRole(MessageRole.Receiver);
                    messagePersonRelation.setMessagable(group);
                    message.getMessagePersonRelations().add(messagePersonRelation);
                }

            } catch (GroupChannelNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else if (choice == 3) {
            System.out.println("Enter the id");
            String id = scanner.nextLine();
            try {
                Channel channel = Channel.findChannelById(id);
                MessageRole messageRole = message.getMessagbleRole(channel);
                if (messageRole == null) {
                    System.out.println("User is already in receiver list");
                } else {
                    MessagePersonRelation messagePersonRelation = new MessagePersonRelation();
                    messagePersonRelation.setRole(MessageRole.Receiver);
                    messagePersonRelation.setMessagable(channel);
                    message.getMessagePersonRelations().add(messagePersonRelation);
                }

            } catch (GroupChannelNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addMessageData() {
        System.out.println("what do you want to send? ");
        System.out.println("1. file");
        System.out.println("2. image");
        System.out.println("3. text");
        System.out.println("4. sticker");
        System.out.println("5. video");
        System.out.println("6. exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 6) {
            return;
        }
        if (choice == 1) {
            System.out.println("Enter the path: ");
            String path = scanner.nextLine();
            FileData fileData = new FileData(path);
            message.setMessageData(fileData);
        } else if (choice == 2) {
            System.out.println("Enter the path: ");
            String path = scanner.nextLine();
            ImageData imageData = new ImageData(path);
            message.setMessageData(imageData);
        } else if (choice == 3) {
            System.out.println("Enter the body: ");
            String body = scanner.nextLine();
            TextData textData = new TextData(body);
            message.setMessageData(textData);
        } else if (choice == 4) {
            System.out.println("Enter the sticker url: ");
            String stickerImageUrl = scanner.nextLine();
            StickerData stickerData = new StickerData(stickerImageUrl);
            message.setMessageData(stickerData);
        } else if (choice == 5) {
            System.out.println("Enter video url");
            String path = scanner.nextLine();
            VideoData videoData = new VideoData(path);
            message.setMessageData(videoData);
        }
    }

    private boolean send(MessageType messageType) {
        if (message.getMessageData() == null) {
            System.out.println("The message should have message data");
            return false;
        } else if (message.getMessagePersonRelations().isEmpty()) {
            System.out.println("You should set receivers for tge message");
            return false;
        }
        MessagePersonRelation messagePersonRelation = new MessagePersonRelation();
        messagePersonRelation.setMessagable(user);
        messagePersonRelation.setRole(MessageRole.Sender);
        message.getMessagePersonRelations().add(messagePersonRelation);
        message.setMessageType(messageType);
        message.setCalendar(Calendar.getInstance());
        message.sendMessage();
        return true;
    }

    public void run(MessageType messageType) {
        while (true) {
            showMessage();
            showMenu();
            System.out.println("Enter a choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 4) {
                return;
            }
            if (choice == 1) {
                addReceiver();
            } else if (choice == 2) {
                addMessageData();
            } else if (choice == 3) {
                send(messageType);
                return;
            }
        }
    }

    private void showMessage() {
        if (message.getMessageData() == null) {
            System.out.println("No data");
        } else {
            System.out.println(message.getMessageData().show());
        }
        if (message.getMessagePersonRelations().isEmpty()) {
            System.out.println("No receiver added");
        } else {
            int i = 1;
            for (MessagePersonRelation messagePersonRelation : message.getMessagePersonRelations()) {
                System.out.println(i + ". " + messagePersonRelation.getMessagable().getString());
                ++i;
            }
        }
    }
}
