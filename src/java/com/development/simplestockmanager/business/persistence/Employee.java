package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employee.findByBornDate", query = "SELECT e FROM Employee e WHERE e.bornDate = :bornDate"),
    @NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByIsOnline", query = "SELECT e FROM Employee e WHERE e.isOnline = :isOnline"),
    @NamedQuery(name = "Employee.findByLastOnlineDate", query = "SELECT e FROM Employee e WHERE e.lastOnlineDate = :lastOnlineDate"),
    @NamedQuery(name = "Employee.findBySessionId", query = "SELECT e FROM Employee e WHERE e.sessionId = :sessionId"),
    @NamedQuery(name = "Employee.findByEnable", query = "SELECT e FROM Employee e WHERE e.enable = :enable"),
    @NamedQuery(name = "Employee.findByCreatedDate", query = "SELECT e FROM Employee e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "Employee.findByLastModifiedDate", query = "SELECT e FROM Employee e WHERE e.lastModifiedDate = :lastModifiedDate")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "BORN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bornDate;
    @Basic(optional = false)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "IS_ONLINE")
    private boolean isOnline;
    @Basic(optional = false)
    @Column(name = "LAST_ONLINE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOnlineDate;
    @Column(name = "SESSION_ID")
    private String sessionId;
    @Basic(optional = false)
    @Column(name = "ENABLE")
    private boolean enable;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<EmployeeType> employeeTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<EmployeeType> employeeTypeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Employee> employeeList;
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee createdUser;
    @JoinColumn(name = "EMPLOYEE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmployeeType employeeType;
    @JoinColumn(name = "SEX_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SexType sexType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Employee> employeeList1;
    @JoinColumn(name = "LAST_MODIFIED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee lastModifiedUser;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Language language;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<SexType> sexTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<SexType> sexTypeList1;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(Employee employee) {
        this.bornDate = employee.bornDate;
        this.email = employee.email;
        this.employeeType = employee.employeeType;
        this.enable = employee.enable;
        this.firstname = employee.firstname;
        this.id = employee.id;
        this.language = employee.language;
        this.lastname = employee.lastname;
        this.password = employee.password;
        this.phone = employee.phone;
        this.sexType = employee.sexType;
        this.username = employee.username;
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

    public boolean getIsOnline() {
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

    public boolean getEnable() {
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @XmlTransient
    public List<EmployeeType> getEmployeeTypeList() {
        return employeeTypeList;
    }

    public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
        this.employeeTypeList = employeeTypeList;
    }

    @XmlTransient
    public List<EmployeeType> getEmployeeTypeList1() {
        return employeeTypeList1;
    }

    public void setEmployeeTypeList1(List<EmployeeType> employeeTypeList1) {
        this.employeeTypeList1 = employeeTypeList1;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Employee createdUser) {
        this.createdUser = createdUser;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    @XmlTransient
    public List<Employee> getEmployeeList1() {
        return employeeList1;
    }

    public void setEmployeeList1(List<Employee> employeeList1) {
        this.employeeList1 = employeeList1;
    }

    public Employee getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Employee lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @XmlTransient
    public List<SexType> getSexTypeList() {
        return sexTypeList;
    }

    public void setSexTypeList(List<SexType> sexTypeList) {
        this.sexTypeList = sexTypeList;
    }

    @XmlTransient
    public List<SexType> getSexTypeList1() {
        return sexTypeList1;
    }

    public void setSexTypeList1(List<SexType> sexTypeList1) {
        this.sexTypeList1 = sexTypeList1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.firstname);
        hash = 43 * hash + Objects.hashCode(this.lastname);
        hash = 43 * hash + Objects.hashCode(this.bornDate);
        hash = 43 * hash + Objects.hashCode(this.phone);
        hash = 43 * hash + Objects.hashCode(this.email);
        hash = 43 * hash + Objects.hashCode(this.username);
        hash = 43 * hash + Objects.hashCode(this.password);
        hash = 43 * hash + (this.enable ? 1 : 0);
        hash = 43 * hash + Objects.hashCode(this.employeeType);
        hash = 43 * hash + Objects.hashCode(this.sexType);
        hash = 43 * hash + Objects.hashCode(this.language);
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
        if (this.enable != other.enable) {
            return false;
        }
        if (!Objects.equals(this.employeeType, other.employeeType)) {
            return false;
        }
        if (!Objects.equals(this.sexType, other.sexType)) {
            return false;
        }
        return Objects.equals(this.language, other.language);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.Employee[ id=" + id + " ]";
    }
    
}
