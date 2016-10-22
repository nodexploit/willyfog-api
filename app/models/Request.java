package models;

import java.util.Date;
import java.util.List;

public class Request extends BaseModel {

    // Category of requests
    public static final String PENDING = "pending";
    public static final String CLOSED = "closed";
    // Status of requests
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";

    private Long student_id;
    private Long origin_subject_id;
    private Long mobility_type_id;
    private List<RequestDestinationSubject> destination_subjects;
    private Date deleted_at;
    private Date updated_at;

    public Long getStudentId() {
        return student_id;
    }

    public void setStudentId(Long student_id) {
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

    public Long getOriginSubjectId() {
        return origin_subject_id;
    }

    public void setOriginSubjectId(Long originSubjectId) {
        this.origin_subject_id = originSubjectId;
    }

    public Long getMobilityTypeId() {
        return mobility_type_id;
    }

    public void setMobilityTypeId(Long mobilityTypeId) {
        this.mobility_type_id = mobilityTypeId;
    }

    public List<RequestDestinationSubject> getDestinationSubjects() {
        return destination_subjects;
    }

    public void setDestinationSubjects(List<RequestDestinationSubject> destinationSubjects) {
        this.destination_subjects = destinationSubjects;
    }
}
