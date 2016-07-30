package models;

import java.util.Date;

public class Request extends BaseModel {

    private Integer student_id;
    private Integer subject_id;
    private Integer subject_eq_id;
    private Date deleted_at;
    private Date updated_at;

    public Integer getStudentId() {
        return student_id;
    }

    public void setStudentId(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getSubjectEqId() {
        return subject_eq_id;
    }

    public void setSubjectEqId(Integer subject_eq_id) {
        this.subject_eq_id = subject_eq_id;
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
