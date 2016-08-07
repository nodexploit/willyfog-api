package models;

import java.util.Date;

public class Role extends BaseModel {

    public static final int ADMIN = 1;
    public static final int COORD = 2;
    public static final int RECOG = 3;
    public static final int STUDENT = 4;

    private Integer permission;
    private String name;
    private Date deleted_at;

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
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
}
