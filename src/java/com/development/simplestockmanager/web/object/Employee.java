package com.development.simplestockmanager.web.object;

import com.development.simplestockmanager.business.persistence.LanguageType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String firstname;
    
    private String lastname;
    
    private Date bornDate;
    
    private String phone;
    
    private String email;
    
    private String username;
    
    private String password;
    
    private boolean isOnline;
    
    private Date lastOnlineDate;
    
    private String sessionId;
    
    private boolean enable;
    
    private Date createdDate;
    
    private String createdUser;
    
    private Date lastModifiedDate;
    
    private String lastModifiedUser;
    
    private long employeeType;
    
    private long sexType;
    
    private LanguageType languageType;

    public Employee() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Date getLastOnlineDate() {
        return lastOnlineDate;
    }

    public void setLastOnlineDate(Date lastOnlineDate) {
        this.lastOnlineDate = lastOnlineDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public long getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(long employeeType) {
        this.employeeType = employeeType;
    }

    public long getSexType() {
        return sexType;
    }

    public void setSexType(long sexType) {
        this.sexType = sexType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.firstname);
        hash = 41 * hash + Objects.hashCode(this.lastname);
        hash = 41 * hash + Objects.hashCode(this.bornDate);
        hash = 41 * hash + Objects.hashCode(this.phone);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + (this.isOnline ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.lastOnlineDate);
        hash = 41 * hash + Objects.hashCode(this.sessionId);
        hash = 41 * hash + (this.enable ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.createdDate);
        hash = 41 * hash + Objects.hashCode(this.createdUser);
        hash = 41 * hash + Objects.hashCode(this.lastModifiedDate);
        hash = 41 * hash + Objects.hashCode(this.lastModifiedUser);
        hash = 41 * hash + (int) (this.employeeType ^ (this.employeeType >>> 32));
        hash = 41 * hash + (int) (this.sexType ^ (this.sexType >>> 32));
        hash = 41 * hash + Objects.hashCode(this.languageType);
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
        final Employee other = (Employee) obj;
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
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.isOnline != other.isOnline) {
            return false;
        }
        if (!Objects.equals(this.lastOnlineDate, other.lastOnlineDate)) {
            return false;
        }
        if (!Objects.equals(this.sessionId, other.sessionId)) {
            return false;
        }
        if (this.enable != other.enable) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.createdUser, other.createdUser)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedDate, other.lastModifiedDate)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedUser, other.lastModifiedUser)) {
            return false;
        }
        if (this.employeeType != other.employeeType) {
            return false;
        }
        if (this.sexType != other.sexType) {
            return false;
        }
        if (!Objects.equals(this.languageType, other.languageType)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.Employee[ id=" + id + " ]";
    }
    
}
