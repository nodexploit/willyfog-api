package models;

import java.util.Date;
import java.util.List;

public class Request extends BaseModel {

    private Integer student_id;
    private Integer origin_subject_id;
    private Integer mobility_id;
    private List<RequestDestinationSubject> destination_subjects;
    private Date deleted_at;
    private Date updated_at;

    public Integer getStudentId() {
        return student_id;
    }

    public void setStudentId(Integer student_id) {
        this.student_id = student_id;
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

    public Integer getOriginSubjectId() {
        return origin_subject_id;
    }

    public void setOriginSubjectId(Integer originSubjectId) {
        this.origin_subject_id = originSubjectId;
    }

    public Integer getMobilityId() {
        return mobility_id;
    }

    public void setMobilityId(Integer mobilityId) {
        this.mobility_id = mobilityId;
    }

    public List<RequestDestinationSubject> getDestinationSubjects() {
        return destination_subjects;
    }

    public void setDestinationSubjects(List<RequestDestinationSubject> destinationSubjects) {
        this.destination_subjects = destinationSubjects;
    }
}
