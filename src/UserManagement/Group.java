package UserManagement;

import MessagingSystem.Messagable;

import java.util.ArrayList;
import java.util.List;

public class Group implements Messagable {
    private static List<Group> groups = new ArrayList<>();
    private String name;
    private String id;
    private ProfileImage profileImage;
    private GroupType groupType;
    private List<UserGroupRelation> userGroupRelationList = new ArrayList<>();

    public Group(String name, String id, ProfileImage profileImage, GroupType groupType) {
        this.name = name;
        this.id = id;
        this.profileImage = profileImage;
        this.groupType = groupType;
    }

    public static List<Group> showAllUserGroups(User user) {
        List<Group> followedGroups = new ArrayList<>();
        for (Group group : groups) {
            for (UserGroupRelation userGroupRelation : group.userGroupRelationList) {
                followedGroups.add(group);
            }
        }
        return followedGroups;
    }

    public void addUser(User user) throws UserFollowedException {
        for (UserGroupRelation userGroupRelation : this.userGroupRelationList) {
            if (userGroupRelation.getUser() == user) {
                throw new UserFollowedException("User already in the group");
            }
        }
        UserGroupRelation userGroupRelation = new UserGroupRelation();
        userGroupRelation.setRole(GroupRole.User);
        userGroupRelation.setUser(user);
        this.userGroupRelationList.add(userGroupRelation);
    }

    public void leaveGroup(User user) throws UserFollowedException {
        for (UserGroupRelation userGroupRelation : this.userGroupRelationList) {
            if (userGroupRelation.getUser() == user) {
                this.userGroupRelationList.remove(userGroupRelation);
                return;
            }
        }
        throw new UserFollowedException("User was not in the group");
    }

    // check for unique id
    public static Group createGroup(User user, String name, String id, ProfileImage profileImage, GroupType groupType) throws GroupChannelExistsException {
        for (Group group : groups) {
            if (group.id.equals(id)) {
                throw new GroupChannelExistsException("Group is already exists");
            }
        }
        Group group = new Group(name, id, profileImage, groupType);
        UserGroupRelation userGroupRelation = new UserGroupRelation();
        userGroupRelation.setUser(user);
        userGroupRelation.setRole(GroupRole.Admin);
        group.userGroupRelationList.add(userGroupRelation);
        groups.add(group);
        return group;
    }

    public static Group findGroupById(String id) throws GroupChannelNotFoundException {
        for (Group group : groups) {
            if (group.id.equals(id)) {
                return group;
            }
        }
        throw new GroupChannelNotFoundException("Group with the given id not found");
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public List<UserGroupRelation> getUserGroupRelationList() {
        return userGroupRelationList;
    }

    @Override
    public String getString() {
        return name;
    }
}
