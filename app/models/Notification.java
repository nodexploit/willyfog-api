package models;

import java.util.Date;

public class Notification extends BaseModel {

    private Long user_id;
    private String content;
    private Date read_at;

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReadAt() {
        return read_at;
    }

    public void setReadAt(Date readAt) {
        this.read_at = readAt;
    }
}
