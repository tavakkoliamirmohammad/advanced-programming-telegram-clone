package DataManagement;

import MessagingSystem.MessageData;

public class TextData implements MessageData {

    private String text;

    public TextData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String show() {
        return text;
    }
}
