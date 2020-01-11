package UserManagement;

import MessagingSystem.Messagable;

public class UserGroupRelation implements Messagable {
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

    @Override
    public String getString() {
        return user.getName();
    }
}
