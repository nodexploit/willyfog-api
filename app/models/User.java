package models;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class User extends BaseModel{

    private String name;
    private String surname;
    private String nif;
    private String email;
    private transient String digest;
    private Date deleted_at;
    private Date updated_at;

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

    public String gravatar() {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (md5 == null) {
            throw new RuntimeException("MD5 digest not available.");
        }

        String emailHash = (new HexBinaryAdapter())
                .marshal(md5.digest(email.getBytes()));

        return "https://www.gravatar.com/avatar/" + emailHash.toLowerCase();
    }

    public boolean isValid() {
        return isSet("name", name) &&
                isSet("surname", surname) &&
                isSet("nif", nif) &&
                isSet("email", email) &&
                isSet("digest", digest);
    }
}
