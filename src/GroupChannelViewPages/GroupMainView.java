package GroupChannelViewPages;

import MessageViewPages.MessageMainView;
import MessagingSystem.Messagable;
import MessagingSystem.Message;
import MessagingSystem.MessageType;
import UserManagement.*;

import java.util.List;
import java.util.Scanner;

public class GroupMainView {


    private Scanner scanner = new Scanner(System.in);

    private Group group;
    private User user;


    public GroupMainView(Group group, User user) {
        this.group = group;
        this.user = user;
    }

    private void showMenu() {
        System.out.println("1. show group info");
        System.out.println("2. show group messages");
        System.out.println("3. leave group");
        System.out.println("4. exit");
    }

    public void run() {
        while (true) {
            showMenu();
            System.out.println("Enter a choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 4) {
                return;
            } else if (choice == 1) {
                showGroupInfo();
            } else if (choice == 2) {
                showGroupMessages();
            } else if (choice == 3) {
                leaveGroup();
                return;
            }
        }
    }


    private void showGroupInfo() {
        System.out.println("name: " + group.getName());
        System.out.println("id: " + group.getId());
        System.out.println("profile picture" + group.getProfileImage().show());
        List<User> users = group.getUserIn();
        for (User u : users) {
            System.out.println(u.getUsername());
        }
    }


    private void showGroupMessages() {
        UserGroupRelation groupRelation = user.getUserGroupRelation(group);
        if (groupRelation == null) {
            return;
        }
        List<Message> messages = Message.getMessagbleMessages(groupRelation);
        if(messages.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        for (Message message : messages) {
            List<Messagable> messagables = message.getSenders();
            System.out.println("----- SENDERS ------ ");
            for (Messagable messagable : messagables) {
                System.out.println(messagable.getString());
            }
            System.out.println();
            System.out.println(message.getMessageData().show());
            System.out.println(message.getCalendar());
        }
        System.out.println("Enter the number reply or forward: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        choice -= 1;
        if (choice < 0 || choice >= messages.size()) {
            System.out.println("Invalid option. try again");
        } else {
            Message message = messages.get(choice);
            MessageMainView messageMainView = new MessageMainView(user);
            System.out.println("What do you want to do? ");
            System.out.println("1. reply");
            System.out.println("2. forward");
            int type = scanner.nextInt();
            scanner.nextLine();
            if (type > 2 || type < 1) {
                System.out.println("invalid option");
                return;
            }
            MessageType messageType = null;
            if (type == 1) {
                messageType = MessageType.Reply;
            } else if (type == 2) {
                messageType = MessageType.Forward;
            }
            Message message1 = messageMainView.run(messageType);
            if (message1 != null) {
                message1.setRelatedMessage(message);
            }
        }
    }

    private void leaveGroup() {
        try {
            group.leaveGroup(user);
        } catch (UserFollowedException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


}
