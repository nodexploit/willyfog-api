package models;

import java.util.Date;

public class Comment extends BaseModel {

    private Integer user_id;
    private Integer request_id;
    private String content;
    private Date deleted_at;
    private Date updated_at;

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer userId) {
        this.user_id = userId;
    }

    public Integer getRequestId() {
        return request_id;
    }

    public void setRequestId(Integer requestId) {
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
}
