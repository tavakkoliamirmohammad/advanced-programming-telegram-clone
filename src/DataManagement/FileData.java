package DataManagement;

import MessagingSystem.MessageData;

public class FileData implements MessageData {
    private String path;

    public FileData(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String show() {
        return path;
    }
}
