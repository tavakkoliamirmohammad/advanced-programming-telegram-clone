package UserManagement;

import MessagingSystem.Messagable;

import java.util.List;

public class UserChannelRelation implements Messagable {
    private User user;
    private GroupRole role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupRole getRole() {
        return role;
    }

    public void setRole(GroupRole role) {
        this.role = role;
    }
}
