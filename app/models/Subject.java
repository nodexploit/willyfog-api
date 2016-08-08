package models;

import java.util.Date;

public class Subject extends BaseModel{

    private Long degree_id;
    private String name;
    private String code;
    private Integer credits;
    private Date deleted_at;
    private Date updated_at;

    public Long getDegreeId() {
        return degree_id;
    }

    public void setDegreeId(Long degree_id) {
        this.degree_id = degree_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
