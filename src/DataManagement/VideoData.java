package DataManagement;

import MessagingSystem.MessageData;

public class VideoData implements MessageData {
    private String videoUrl;

    public VideoData(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String show() {
        return videoUrl;
    }
}
