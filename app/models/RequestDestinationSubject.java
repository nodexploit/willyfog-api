package models;

import java.util.Date;

public class RequestDestinationSubject extends BaseModel {

    private Integer request_id;
    private Integer subject_id;
    private Integer city_id;
    private String subject_name;
    private String subject_code;
    private Integer subject_credits;
    private String code;
    private String centre_name;
    private String centre_code;
    private String uri;
    private String attachment;
    private Date deleted_at;
    private Date updated_at;

    public Integer getRequestId() {
        return request_id;
    }

    public void setRequestId(Integer requestId) {
        this.request_id = requestId;
    }

    public Integer getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(Integer subjectId) {
        this.subject_id = subjectId;
    }

    public Integer getCityId() {
        return city_id;
    }

    public void setCityId(Integer cityId) {
        this.city_id = cityId;
    }

    public String getSubjectName() {
        return subject_name;
    }

    public void setSubjectName(String subjectName) {
        this.subject_name = subjectName;
    }

    public Integer getSubjectCredits() {
        return subject_credits;
    }

    public void setSubjectCredits(Integer subjectCredits) {
        this.subject_credits = subjectCredits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCentreName() {
        return centre_name;
    }

    public void setCentreName(String centreName) {
        this.centre_name = centreName;
    }

    public String getCentreCode() {
        return centre_code;
    }

    public void setCentreCode(String centreCode) {
        this.centre_code = centreCode;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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

    public String getSubjectCode() {
        return subject_code;
    }

    public void setSubjectCode(String subjectCode) {
        this.subject_code = subjectCode;
    }
}
