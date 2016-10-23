package http;

public class SuccessReponse {

    public final String status;
    public final Object data;

    public SuccessReponse(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public SuccessReponse(String status) {
        this.status = status;
        this.data = null;
    }
}
