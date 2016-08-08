package models;

import java.util.Date;

public class City extends BaseModel {

    private String name;
    private Long country_id;
    private Date deleted_at;
    private Date updated_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountryId() {
        return country_id;
    }

    public void setCountryId(Long country_id) {
        this.country_id = country_id;
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
