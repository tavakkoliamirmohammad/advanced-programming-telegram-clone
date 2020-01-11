package DataManagement;

import MessagingSystem.MessageData;

public class ImageData implements MessageData {
    private String imageUrl;

    public ImageData(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String show() {
        return imageUrl;
    }
}
