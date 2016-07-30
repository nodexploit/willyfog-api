package models;

import java.util.Date;

public class Subject extends BaseModel{

    private Integer degree_id;
    private String name;
    private Date deleted_at;
    private Date updated_at;

    public Integer getDegreeId() {
        return degree_id;
    }

    public void setDegreeId(Integer degree_id) {
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
}
