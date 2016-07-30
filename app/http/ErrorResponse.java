package http;

public class ErrorResponse {

    public final String status;
    public final String messages;

    public ErrorResponse(String status, String messages) {
        this.status = status;
        this.messages = messages;
    }
}
