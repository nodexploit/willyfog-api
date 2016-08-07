package models;

import java.util.Date;

public class RequestDestinationSubject extends BaseModel {

    private Long request_id;
    private Integer subject_id;
    private String subject_name;
    private String subject_code;
    private Integer subject_credits;
    private String centre_name;
    private String country_name;
    private String city_name;
    private String university_name;
    private String degree_name;
    private String uri;
    private String attachment;
    private Date deleted_at;
    private Date updated_at;

    public Long getRequestId() {
        return request_id;
    }

    public void setRequestId(Long requestId) {
        this.request_id = requestId;
    }

    public Integer getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(Integer subjectId) {
        this.subject_id = subjectId;
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

    public String getCountryName() {
        return country_name;
    }

    public void setCountryName(String countryName) {
        this.country_name = countryName;
    }

    public String getCityName() {
        return city_name;
    }

    public void setCityName(String cityName) {
        this.city_name = cityName;
    }

    public String getUniversityName() {
        return university_name;
    }

    public void setUniversityName(String universityName) {
        this.university_name = universityName;
    }

    public String getDegreeName() {
        return degree_name;
    }

    public void setDegreeName(String degreeName) {
        this.degree_name = degreeName;
    }

    public String getCentreName() {
        return centre_name;
    }

    public void setCentreName(String centreName) {
        this.centre_name = centreName;
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
