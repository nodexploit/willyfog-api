package models;

import java.util.Date;

public class Comment extends BaseModel {

    private Long user_id;
    private Long request_id;
    private String content;
    private Date deleted_at;
    private Date updated_at;

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public Long getRequestId() {
        return request_id;
    }

    public void setRequestId(Long requestId) {
        this.request_id = requestId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDeletedAt() {
        return deleted_at;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deleted_at = deletedAt;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updated_at = updatedAt;
    }

    public boolean isValid() {
        return isSet("content", content);
    }
}
