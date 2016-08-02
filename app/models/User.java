package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends BaseModel{

    private String name;
    private String surname;
    private String nif;
    private String email;
    private transient String digest;
    private Date deleted_at;
    private Date updated_at;

    private List<String> errors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
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

    public List<String> getErrors() {
        return errors;
    }

    public boolean isValid() {
        return name != null && surname != null &&
                nif != null && email != null &&
                digest != null;
    }
}
