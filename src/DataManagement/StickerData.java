package DataManagement;

import MessagingSystem.MessageData;

public class StickerData implements MessageData {
    private String stickerImageUrl;

    public StickerData(String stickerImageUrl) {
        this.stickerImageUrl = stickerImageUrl;
    }

    public String getStickerImageUrl() {
        return stickerImageUrl;
    }

    public void setStickerImageUrl(String stickerImageUrl) {
        this.stickerImageUrl = stickerImageUrl;
    }

    @Override
    public String show() {
        return stickerImageUrl;
    }
}
