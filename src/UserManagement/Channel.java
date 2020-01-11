package UserManagement;

import MessagingSystem.Messagable;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Messagable {
    private static List<Channel> channels = new ArrayList<>();
    private String name;
    private String id;
    private ProfileImage profileImage;
    private GroupType groupType;
    private List<UserChannelRelation> userChannelRelations = new ArrayList<>();

    public Channel(String name, String id, ProfileImage profileImage, GroupType groupType) {
        this.name = name;
        this.id = id;
        this.profileImage = profileImage;
        this.groupType = groupType;
    }

    public static List<Channel> getChannels() {
        return channels;
    }

    public static void setChannels(List<Channel> channels) {
        Channel.channels = channels;
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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public List<UserChannelRelation> getUserChannelRelations() {
        return userChannelRelations;
    }

    public void setUserChannelRelations(List<UserChannelRelation> userChannelRelations) {
        this.userChannelRelations = userChannelRelations;
    }

    public static List<Channel> showAllUserChannel(User user) {
        List<Channel> followedChannel = new ArrayList<>();
        for (Channel channel : channels) {
            for (UserChannelRelation userChannelRelation : channel.userChannelRelations) {
                if (userChannelRelation.getUser() == user) {
                    followedChannel.add(channel);
                }
            }
        }
        return followedChannel;
    }

    public void addUser(User user) throws UserFollowedException {
        for (UserChannelRelation userChannelRelation : this.userChannelRelations) {
            if (userChannelRelation.getUser() == user) {
                throw new UserFollowedException("User is already in the channel");
            }
        }
        UserChannelRelation userChannelRelation = new UserChannelRelation();
        userChannelRelation.setUser(user);
        userChannelRelation.setRole(GroupRole.User);
        channels.add(this);
    }

    public void leaveChannel(User user) throws UserFollowedException {
        for (UserChannelRelation userChannelRelation : this.userChannelRelations) {
            if (userChannelRelation.getUser() == user) {
                this.userChannelRelations.remove(userChannelRelation);
                return;
            }
        }
        throw new UserFollowedException("User was not in the channel");
    }

    public static Channel createChannel(User user, String name, String id, ProfileImage profileImage, GroupType groupType) throws GroupChannelExistsException {
        for (Channel channel : channels) {
            if (channel.id.equals(id)) {
                throw new GroupChannelExistsException("Channel is already exists");
            }
        }
        Channel channel = new Channel(name, id, profileImage, groupType);
        UserChannelRelation userChannelRelation = new UserChannelRelation();
        userChannelRelation.setUser(user);
        userChannelRelation.setRole(GroupRole.Admin);
        channel.userChannelRelations.add(userChannelRelation);
        channels.add(channel);
        return channel;
    }

    public static Channel findChannelById(String id) throws GroupChannelNotFoundException {
        for (Channel channel : channels) {
            if (channel.id.equals(id)) {
                return channel;
            }
        }
        throw new GroupChannelNotFoundException("Channel with the give id not found");
    }

    @Override
    public String getString() {
        return name;
    }
}
