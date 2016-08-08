package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseModel {

    private Long id;
    private Date created_at;

    protected transient List<String> errors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date createdAt) {
        this.created_at = createdAt;
    }

    public List<String> getErrors() {
        return errors;
    }

    /**
     * Validations
     */

    protected boolean isSet(String key, String value) {
        boolean valid = value != null && value.length() > 0;

        if (!valid) {
            errors.add(key + " field is not set");
        }

        return valid;
    }
}
