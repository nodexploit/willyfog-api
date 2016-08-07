package models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class OAuthAccessToken {

    private String access_token;
    private Long user_id;
    private String client_id;
    private Date expires;
    private String scope;
    private Date created_at;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public String getClientId() {
        return client_id;
    }

    public void setClientId(String clientId) {
        this.client_id = clientId;
    }

    public LocalDateTime getExpires() {
        return expires.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public LocalDateTime getCreatedAt() {
        return created_at.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setCreatedAt(Date createdAt) {
        this.created_at = createdAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().compareTo(getExpires()) > 0;
    }
}
