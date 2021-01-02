package communicatorshared;

public class WebSocketMessage {
    private final WebSocketMessageType type;
    private final String content;

    public WebSocketMessage(WebSocketMessageType type, String content) {
        this.type = type;
        this.content = content;
    }

    public WebSocketMessageType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
