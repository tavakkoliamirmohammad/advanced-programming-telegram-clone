package GroupChannelViewPages;

import MessagingSystem.Messagable;
import MessagingSystem.Message;
import UserManagement.Channel;
import UserManagement.User;
import UserManagement.UserChannelRelation;
import UserManagement.UserFollowedException;

import java.util.List;
import java.util.Scanner;

public class ChannelMainView {

    private Scanner scanner = new Scanner(System.in);

    private Channel channel;
    private User user;

    public ChannelMainView(Channel channel, User user) {
        this.channel = channel;
        this.user = user;
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
                showChannelInfo();
            } else if (choice == 2) {
                showChannelMessages();
            } else if (choice == 3) {
                leaveChannel();
                return;
            }
        }
    }

    public void showMenu() {
        System.out.println("1. show channel info");
        System.out.println("2. send message");
        System.out.println("3. show channel messages");
    }

    private void showChannelInfo() {
        System.out.println("name: " + channel.getName());
        System.out.println("id: " + channel.getId());
        System.out.println("profile picture" + channel.getProfileImage().show());
        List<User> users = channel.getUserIn();
        for (User u : users) {
            System.out.println(u.getUsername());
        }
    }


    private void showChannelMessages() {
        UserChannelRelation channelRelation = user.getUserChannelRelation(channel);
        if (channelRelation == null) {
            return;
        }
        List<Message> messages = Message.getMessagbleMessages(channelRelation);
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
    }

    private void leaveChannel() {
        try {
            channel.leaveChannel(user);
        } catch (UserFollowedException e) {
            System.out.println(e.getMessage());
        }
    }
}
