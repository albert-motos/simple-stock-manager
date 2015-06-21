package com.development.simplestockmanager.web.object;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Client implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstname;

    private String lastname;

    private Date bornDate;

    private String phone;

    private String email;

    private boolean enable;

    private Date createdDate;

    private String createdUser;

    private Date lastModifiedDate;

    private String lastModifiedUser;
    
    private long sexType;

    public Client() {
    }
    
    public Client(Long id, String firstname, String lastname, Date bornDate, String phone, String email, boolean enable, Date createdDate, String createdUser, Date lastModifiedDate, String lastModifiedUser) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornDate = bornDate;
        this.phone = phone;
        this.email = email;
        this.enable = enable;
        this.createdDate = createdDate;
        this.createdUser = createdUser;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedUser = lastModifiedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public long getSexType() {
        return sexType;
    }

    public void setSexType(long sexType) {
        this.sexType = sexType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.firstname);
        hash = 17 * hash + Objects.hashCode(this.lastname);
        hash = 17 * hash + Objects.hashCode(this.bornDate);
        hash = 17 * hash + Objects.hashCode(this.phone);
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + (this.enable ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.createdDate);
        hash = 17 * hash + Objects.hashCode(this.createdUser);
        hash = 17 * hash + Objects.hashCode(this.lastModifiedDate);
        hash = 17 * hash + Objects.hashCode(this.lastModifiedUser);
        hash = 17 * hash + (int) (this.sexType ^ (this.sexType >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.bornDate, other.bornDate)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (this.enable != other.enable) {
            return false;
        }
        return this.sexType == other.sexType;
    }
    
}
