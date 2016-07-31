package models;

import java.util.Date;

public class University extends BaseModel {

    private String name;
    private String code;
    private Date deleted_at;
    private Date updated_at;
    private Integer city_id;

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

    public Integer getCityId() {
        return city_id;
    }

    public void setCityId(Integer cityId) {
        this.city_id = cityId;
    }
}
