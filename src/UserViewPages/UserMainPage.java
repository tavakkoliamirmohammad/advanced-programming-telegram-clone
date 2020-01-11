package UserViewPages;

import MessageViewPages.MessageMainView;
import MessagingSystem.Messagable;
import MessagingSystem.Message;
import MessagingSystem.MessageType;
import UserManagement.Channel;
import UserManagement.Group;
import UserManagement.User;

import java.util.List;
import java.util.Scanner;

public class UserMainPage {
    private static Scanner scanner = new Scanner(System.in);
    private User user;

    public UserMainPage(User user) {
        this.user = user;
    }

    private void showMenu() {
        System.out.println("1. show groups");
        System.out.println("2. show channels");
        System.out.println("3. show messages");
        System.out.println("4. send message");
        System.out.println("5. show profile");
        System.out.println("6. exit");
    }

    public void run() {
        System.out.println("Welcome " + user.getName().toUpperCase());

        while (true) {
            showMenu();
            System.out.println("Enter a choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 6) {
                return;
            }
            if (choice == 1) {
                showGroups();
            } else if (choice == 2) {
                showChannels();
            } else if (choice == 3) {
                getUserMessages();
            } else if (choice == 4) {
                MessageMainView messageMainView = new MessageMainView(user);
                messageMainView.run(MessageType.Ordinary);
            }
            else if(choice == 5){
                showProfile();
            }
        }
    }

    private void showGroups() {
        List<Group> groups = Group.showAllUserGroups(user);
        for (int i = 0; i < groups.size(); ++i) {
            System.out.println((i + 1) + groups.get(i).getName());
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        choice -= 1;
        if (choice < 0 || choice >= groups.size()) {
            System.out.println("Invalid option. try again");
        } else {
            Group group = groups.get(choice);
        }
    }

    private void showChannels() {
        List<Channel> channels = Channel.showAllUserChannel(user);
        for (int i = 0; i < channels.size(); ++i) {
            System.out.println((i + 1) + channels.get(i).getName());
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        choice -= 1;
        if (choice < 0 || choice >= channels.size()) {
            System.out.println("Invalid option. try again");
        } else {
            Channel channel = channels.get(choice);
        }
    }

    private void getUserMessages() {
        List<Message> messages = Message.getMessagbleMessages(user);
        for (Message message : messages) {
            System.out.println(message.getMessageData().show());
            System.out.println(message.getCalendar());
            List<Messagable> messagables = message.getSenders();
            System.out.println("-------- SENDERS --------");
            System.out.println();
            for (Messagable messagable : messagables) {
                System.out.println(messagable.getString());
            }

            System.out.println("-------------------------");
        }
    }

    private void showProfile() {
        System.out.println("name: " + user.getName());
        System.out.println("username: " + user.getUsername());
        System.out.println("id: " + user.getId());
        System.out.println("profile image: " + user.getProfileImage());
    }
}
