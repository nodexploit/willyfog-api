package http;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    public final String status;
    public final List<String> messages;

    public ErrorResponse(String status, List<String> messages) {
        this.status = status;
        this.messages = messages;
    }

    public ErrorResponse(String status, String messages) {
        this(status, new ArrayList<String>() {{ add(messages); }});
    }
}
