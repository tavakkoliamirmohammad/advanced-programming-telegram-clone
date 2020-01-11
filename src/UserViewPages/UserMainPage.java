package UserViewPages;

import GroupChannelViewPages.ChannelMainView;
import GroupChannelViewPages.GroupMainView;
import MessageViewPages.MessageMainView;
import MessagingSystem.Messagable;
import MessagingSystem.Message;
import MessagingSystem.MessageType;
import UserManagement.*;

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
        System.out.println("6. create group");
        System.out.println("7. create channel");
        System.out.println("8. logout");
    }

    public void run() {
        System.out.println("Welcome " + user.getName().toUpperCase());

        while (true) {
            showMenu();
            System.out.println("Enter a choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 8) {
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
            } else if (choice == 5) {
                showProfile();
            }
            else if(choice == 6){
                createGroup();
            }
            else if (choice == 7){
                createChannel();
            }
        }
    }

    private void showGroups() {
        List<Group> groups = Group.showAllUserGroups(user);
        if (groups.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (int i = 0; i < groups.size(); ++i) {
            System.out.println((i + 1) + ". " +  groups.get(i).getName());
        }
        System.out.println("Choice one: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        choice -= 1;
        if (choice < 0 || choice >= groups.size()) {
            System.out.println("Invalid option. try again");
        } else {
            Group group = groups.get(choice);
            GroupMainView groupMainView = new GroupMainView(group, user);
            groupMainView.run();
        }
    }

    private void showChannels() {
        List<Channel> channels = Channel.showAllUserChannel(user);
        if (channels.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (int i = 0; i < channels.size(); ++i) {
            System.out.println((i + 1) + ". " +  channels.get(i).getName());
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        choice -= 1;
        if (choice < 0 || choice >= channels.size()) {
            System.out.println("Invalid option. try again");
        } else {
            Channel channel = channels.get(choice);
            ChannelMainView channelMainView = new ChannelMainView(channel, user);
            channelMainView.run();
        }
    }

    private void getUserMessages() {
        List<Message> messages = Message.getMessagbleMessages(user);
        if(messages.isEmpty()){
            System.out.println("Empty!");
            return;
        }
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
        System.out.println("profile image: " + user.getProfileImage().show());
    }

    private void createGroup(){
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter id");
        String id = scanner.nextLine();
        System.out.println("Enter profile picture url");
        String profile = scanner.nextLine();
        ProfileImage profileImage = new ProfileImage(profile);
        System.out.println("choice group type: ");
        System.out.println("1. private");
        System.out.println("2. public");
        int choice = scanner.nextInt();
        scanner.nextLine();
        GroupType groupType = null;
        if(choice == 1){
            groupType = GroupType.Private;
        }
        else if(choice == 2){
            groupType = GroupType.Public;
        }
        else {
            System.out.println("Invalid option try again");
            return;
        }
        try {
            Group group = Group.createGroup(user, name, id, profileImage, groupType);
        } catch (GroupChannelExistsException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createChannel(){
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter id");
        String id = scanner.nextLine();
        System.out.println("Enter profile picture url");
        String profile = scanner.nextLine();
        ProfileImage profileImage = new ProfileImage(profile);
        System.out.println("choice Channel type: ");
        System.out.println("1. private");
        System.out.println("2. public");
        int choice = scanner.nextInt();
        scanner.nextLine();
        GroupType groupType = null;
        if(choice == 1){
            groupType = GroupType.Private;
        }
        else if(choice == 2){
            groupType = GroupType.Public;
        }
        else {
            System.out.println("Invalid option try again");
            return;
        }
        try {
            Channel channel = Channel.createChannel(user, name, id, profileImage, groupType);
        } catch (GroupChannelExistsException e) {
            System.out.println(e.getMessage());
        }
    }
}
