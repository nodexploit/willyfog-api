package models;

import java.util.Date;

public class Centre extends BaseModel {

    private String name;
    private String code;
    private Long university_id;
    private Date deleted_at;
    private Date updated_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUniversityId() {
        return university_id;
    }

    public void setUniversityId(Long universityId) {
        this.university_id = universityId;
    }

    public Date getDeletedAt() {
        return deleted_at;
    }

    public void setDeletedAt(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }
}
