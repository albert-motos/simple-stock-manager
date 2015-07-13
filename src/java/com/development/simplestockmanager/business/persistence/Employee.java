/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private List<Price> priceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Price> priceList1;
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
    @JoinColumn(name = "LANGUAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Language languageType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<PriceType> priceTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<PriceType> priceTypeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Record> recordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Record> recordList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Record> recordList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Invoice> invoiceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Invoice> invoiceList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Invoice> invoiceList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Client> clientList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Client> clientList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Stock> stockList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Stock> stockList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Brand> brandList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Brand> brandList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<SexType> sexTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<SexType> sexTypeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<PaymentType> paymentTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<PaymentType> paymentTypeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<EmployeeType> employeeTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<EmployeeType> employeeTypeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Item> itemList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<ProductType> productTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<ProductType> productTypeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<AnalyticsTime> analyticsTimeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<AnalyticsTime> analyticsTimeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Product> productList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Product> productList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Provider> providerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Provider> providerList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Store> storeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Store> storeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedUser")
    private List<Store> storeList2;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(Long id, String firstname, String lastname, Date bornDate, String phone, String email, String username, String password, boolean isOnline, Date lastOnlineDate, boolean enable, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornDate = bornDate;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isOnline = isOnline;
        this.lastOnlineDate = lastOnlineDate;
        this.enable = enable;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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
    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    @XmlTransient
    public List<Price> getPriceList1() {
        return priceList1;
    }

    public void setPriceList1(List<Price> priceList1) {
        this.priceList1 = priceList1;
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

    public Language getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Language languageType) {
        this.languageType = languageType;
    }

    @XmlTransient
    public List<PriceType> getPriceTypeList() {
        return priceTypeList;
    }

    public void setPriceTypeList(List<PriceType> priceTypeList) {
        this.priceTypeList = priceTypeList;
    }

    @XmlTransient
    public List<PriceType> getPriceTypeList1() {
        return priceTypeList1;
    }

    public void setPriceTypeList1(List<PriceType> priceTypeList1) {
        this.priceTypeList1 = priceTypeList1;
    }

    @XmlTransient
    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @XmlTransient
    public List<Record> getRecordList1() {
        return recordList1;
    }

    public void setRecordList1(List<Record> recordList1) {
        this.recordList1 = recordList1;
    }

    @XmlTransient
    public List<Record> getRecordList2() {
        return recordList2;
    }

    public void setRecordList2(List<Record> recordList2) {
        this.recordList2 = recordList2;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList1() {
        return invoiceList1;
    }

    public void setInvoiceList1(List<Invoice> invoiceList1) {
        this.invoiceList1 = invoiceList1;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList2() {
        return invoiceList2;
    }

    public void setInvoiceList2(List<Invoice> invoiceList2) {
        this.invoiceList2 = invoiceList2;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    public List<Client> getClientList1() {
        return clientList1;
    }

    public void setClientList1(List<Client> clientList1) {
        this.clientList1 = clientList1;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @XmlTransient
    public List<Stock> getStockList1() {
        return stockList1;
    }

    public void setStockList1(List<Stock> stockList1) {
        this.stockList1 = stockList1;
    }

    @XmlTransient
    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    @XmlTransient
    public List<Brand> getBrandList1() {
        return brandList1;
    }

    public void setBrandList1(List<Brand> brandList1) {
        this.brandList1 = brandList1;
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

    @XmlTransient
    public List<PaymentType> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<PaymentType> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    @XmlTransient
    public List<PaymentType> getPaymentTypeList1() {
        return paymentTypeList1;
    }

    public void setPaymentTypeList1(List<PaymentType> paymentTypeList1) {
        this.paymentTypeList1 = paymentTypeList1;
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
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @XmlTransient
    public List<Item> getItemList1() {
        return itemList1;
    }

    public void setItemList1(List<Item> itemList1) {
        this.itemList1 = itemList1;
    }

    @XmlTransient
    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }

    @XmlTransient
    public List<ProductType> getProductTypeList1() {
        return productTypeList1;
    }

    public void setProductTypeList1(List<ProductType> productTypeList1) {
        this.productTypeList1 = productTypeList1;
    }

    @XmlTransient
    public List<AnalyticsTime> getAnalyticsTimeList() {
        return analyticsTimeList;
    }

    public void setAnalyticsTimeList(List<AnalyticsTime> analyticsTimeList) {
        this.analyticsTimeList = analyticsTimeList;
    }

    @XmlTransient
    public List<AnalyticsTime> getAnalyticsTimeList1() {
        return analyticsTimeList1;
    }

    public void setAnalyticsTimeList1(List<AnalyticsTime> analyticsTimeList1) {
        this.analyticsTimeList1 = analyticsTimeList1;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @XmlTransient
    public List<Product> getProductList1() {
        return productList1;
    }

    public void setProductList1(List<Product> productList1) {
        this.productList1 = productList1;
    }

    @XmlTransient
    public List<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<Provider> providerList) {
        this.providerList = providerList;
    }

    @XmlTransient
    public List<Provider> getProviderList1() {
        return providerList1;
    }

    public void setProviderList1(List<Provider> providerList1) {
        this.providerList1 = providerList1;
    }

    @XmlTransient
    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    @XmlTransient
    public List<Store> getStoreList1() {
        return storeList1;
    }

    public void setStoreList1(List<Store> storeList1) {
        this.storeList1 = storeList1;
    }

    @XmlTransient
    public List<Store> getStoreList2() {
        return storeList2;
    }

    public void setStoreList2(List<Store> storeList2) {
        this.storeList2 = storeList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.Employee[ id=" + id + " ]";
    }
    
}
