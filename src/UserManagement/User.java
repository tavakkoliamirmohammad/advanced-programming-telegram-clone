package UserManagement;


import MessagingSystem.Messagable;

import java.util.ArrayList;
import java.util.List;

public class User implements Messagable {

    private static List<User> users = new ArrayList<>();

    private String username;
    private String password;
    private String name;
    private String id;
    private ProfileImage profileImage;

    public User(String username, String password, String name, String id, ProfileImage profileImage) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.id = id;
        this.profileImage = profileImage;
    }

    public static User login(String username, String password) throws InvalidUserAuthenticationException {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        throw new InvalidUserAuthenticationException("Username or password is wrong!");
    }

    public static User signUp(String username, String password, String name, String id, ProfileImage profileImage) throws InvalidUserAuthenticationException {
        for (User user : users) {
            if (user.id.equals(id) || user.username.equals(username)) {
                throw new InvalidUserAuthenticationException("Username or id is already taken");
            }
        }
        User us = new User(username, password, name, id, profileImage);
        users.add(us);
        return us;
    }

    public static User findById(String id) throws InvalidUserAuthenticationException {
        for (User user : users) {
            if (user.id.equals(id)) {
                return user;
            }
        }
        throw new InvalidUserAuthenticationException("user not found");
    }

    public UserGroupRelation getUserGroupRelation(Group group) {
        UserGroupRelation groupRelation = null;
        for (UserGroupRelation userGroupRelation : group.getUserGroupRelationList()) {
            if (userGroupRelation.getUser() == this) {
                groupRelation = userGroupRelation;
            }
        }
        return groupRelation;
    }

    public UserChannelRelation getUserChannelRelation(Channel channel) {
        UserChannelRelation channelRelation = null;
        for (UserChannelRelation userChannelRelation : channel.getUserChannelRelations()) {
            if (userChannelRelation.getUser() == this) {
                channelRelation = userChannelRelation;
            }
        }
        return channelRelation;
    }

    @Override
    public String getString() {
        return username;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        User.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}
