/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.Price;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.AnalyticsTime;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) {
        if (employee.getPriceList() == null) {
            employee.setPriceList(new ArrayList<Price>());
        }
        if (employee.getPriceList1() == null) {
            employee.setPriceList1(new ArrayList<Price>());
        }
        if (employee.getEmployeeList() == null) {
            employee.setEmployeeList(new ArrayList<Employee>());
        }
        if (employee.getEmployeeList1() == null) {
            employee.setEmployeeList1(new ArrayList<Employee>());
        }
        if (employee.getPriceTypeList() == null) {
            employee.setPriceTypeList(new ArrayList<PriceType>());
        }
        if (employee.getPriceTypeList1() == null) {
            employee.setPriceTypeList1(new ArrayList<PriceType>());
        }
        if (employee.getRecordList() == null) {
            employee.setRecordList(new ArrayList<Record>());
        }
        if (employee.getRecordList1() == null) {
            employee.setRecordList1(new ArrayList<Record>());
        }
        if (employee.getRecordList2() == null) {
            employee.setRecordList2(new ArrayList<Record>());
        }
        if (employee.getInvoiceList() == null) {
            employee.setInvoiceList(new ArrayList<Invoice>());
        }
        if (employee.getInvoiceList1() == null) {
            employee.setInvoiceList1(new ArrayList<Invoice>());
        }
        if (employee.getInvoiceList2() == null) {
            employee.setInvoiceList2(new ArrayList<Invoice>());
        }
        if (employee.getClientList() == null) {
            employee.setClientList(new ArrayList<Client>());
        }
        if (employee.getClientList1() == null) {
            employee.setClientList1(new ArrayList<Client>());
        }
        if (employee.getStockList() == null) {
            employee.setStockList(new ArrayList<Stock>());
        }
        if (employee.getStockList1() == null) {
            employee.setStockList1(new ArrayList<Stock>());
        }
        if (employee.getBrandList() == null) {
            employee.setBrandList(new ArrayList<Brand>());
        }
        if (employee.getBrandList1() == null) {
            employee.setBrandList1(new ArrayList<Brand>());
        }
        if (employee.getSexTypeList() == null) {
            employee.setSexTypeList(new ArrayList<SexType>());
        }
        if (employee.getSexTypeList1() == null) {
            employee.setSexTypeList1(new ArrayList<SexType>());
        }
        if (employee.getPaymentTypeList() == null) {
            employee.setPaymentTypeList(new ArrayList<PaymentType>());
        }
        if (employee.getPaymentTypeList1() == null) {
            employee.setPaymentTypeList1(new ArrayList<PaymentType>());
        }
        if (employee.getEmployeeTypeList() == null) {
            employee.setEmployeeTypeList(new ArrayList<EmployeeType>());
        }
        if (employee.getEmployeeTypeList1() == null) {
            employee.setEmployeeTypeList1(new ArrayList<EmployeeType>());
        }
        if (employee.getItemList() == null) {
            employee.setItemList(new ArrayList<Item>());
        }
        if (employee.getItemList1() == null) {
            employee.setItemList1(new ArrayList<Item>());
        }
        if (employee.getProductTypeList() == null) {
            employee.setProductTypeList(new ArrayList<ProductType>());
        }
        if (employee.getProductTypeList1() == null) {
            employee.setProductTypeList1(new ArrayList<ProductType>());
        }
        if (employee.getAnalyticsTimeList() == null) {
            employee.setAnalyticsTimeList(new ArrayList<AnalyticsTime>());
        }
        if (employee.getAnalyticsTimeList1() == null) {
            employee.setAnalyticsTimeList1(new ArrayList<AnalyticsTime>());
        }
        if (employee.getProductList() == null) {
            employee.setProductList(new ArrayList<Product>());
        }
        if (employee.getProductList1() == null) {
            employee.setProductList1(new ArrayList<Product>());
        }
        if (employee.getProviderList() == null) {
            employee.setProviderList(new ArrayList<Provider>());
        }
        if (employee.getProviderList1() == null) {
            employee.setProviderList1(new ArrayList<Provider>());
        }
        if (employee.getStoreList() == null) {
            employee.setStoreList(new ArrayList<Store>());
        }
        if (employee.getStoreList1() == null) {
            employee.setStoreList1(new ArrayList<Store>());
        }
        if (employee.getStoreList2() == null) {
            employee.setStoreList2(new ArrayList<Store>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = employee.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                employee.setCreatedUser(createdUser);
            }
            EmployeeType employeeType = employee.getEmployeeType();
            if (employeeType != null) {
                employeeType = em.getReference(employeeType.getClass(), employeeType.getId());
                employee.setEmployeeType(employeeType);
            }
            SexType sexType = employee.getSexType();
            if (sexType != null) {
                sexType = em.getReference(sexType.getClass(), sexType.getId());
                employee.setSexType(sexType);
            }
            Employee lastModifiedUser = employee.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                employee.setLastModifiedUser(lastModifiedUser);
            }
            Language languageType = employee.getLanguageType();
            if (languageType != null) {
                languageType = em.getReference(languageType.getClass(), languageType.getId());
                employee.setLanguageType(languageType);
            }
            List<Price> attachedPriceList = new ArrayList<Price>();
            for (Price priceListPriceToAttach : employee.getPriceList()) {
                priceListPriceToAttach = em.getReference(priceListPriceToAttach.getClass(), priceListPriceToAttach.getId());
                attachedPriceList.add(priceListPriceToAttach);
            }
            employee.setPriceList(attachedPriceList);
            List<Price> attachedPriceList1 = new ArrayList<Price>();
            for (Price priceList1PriceToAttach : employee.getPriceList1()) {
                priceList1PriceToAttach = em.getReference(priceList1PriceToAttach.getClass(), priceList1PriceToAttach.getId());
                attachedPriceList1.add(priceList1PriceToAttach);
            }
            employee.setPriceList1(attachedPriceList1);
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : employee.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getId());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            employee.setEmployeeList(attachedEmployeeList);
            List<Employee> attachedEmployeeList1 = new ArrayList<Employee>();
            for (Employee employeeList1EmployeeToAttach : employee.getEmployeeList1()) {
                employeeList1EmployeeToAttach = em.getReference(employeeList1EmployeeToAttach.getClass(), employeeList1EmployeeToAttach.getId());
                attachedEmployeeList1.add(employeeList1EmployeeToAttach);
            }
            employee.setEmployeeList1(attachedEmployeeList1);
            List<PriceType> attachedPriceTypeList = new ArrayList<PriceType>();
            for (PriceType priceTypeListPriceTypeToAttach : employee.getPriceTypeList()) {
                priceTypeListPriceTypeToAttach = em.getReference(priceTypeListPriceTypeToAttach.getClass(), priceTypeListPriceTypeToAttach.getId());
                attachedPriceTypeList.add(priceTypeListPriceTypeToAttach);
            }
            employee.setPriceTypeList(attachedPriceTypeList);
            List<PriceType> attachedPriceTypeList1 = new ArrayList<PriceType>();
            for (PriceType priceTypeList1PriceTypeToAttach : employee.getPriceTypeList1()) {
                priceTypeList1PriceTypeToAttach = em.getReference(priceTypeList1PriceTypeToAttach.getClass(), priceTypeList1PriceTypeToAttach.getId());
                attachedPriceTypeList1.add(priceTypeList1PriceTypeToAttach);
            }
            employee.setPriceTypeList1(attachedPriceTypeList1);
            List<Record> attachedRecordList = new ArrayList<Record>();
            for (Record recordListRecordToAttach : employee.getRecordList()) {
                recordListRecordToAttach = em.getReference(recordListRecordToAttach.getClass(), recordListRecordToAttach.getId());
                attachedRecordList.add(recordListRecordToAttach);
            }
            employee.setRecordList(attachedRecordList);
            List<Record> attachedRecordList1 = new ArrayList<Record>();
            for (Record recordList1RecordToAttach : employee.getRecordList1()) {
                recordList1RecordToAttach = em.getReference(recordList1RecordToAttach.getClass(), recordList1RecordToAttach.getId());
                attachedRecordList1.add(recordList1RecordToAttach);
            }
            employee.setRecordList1(attachedRecordList1);
            List<Record> attachedRecordList2 = new ArrayList<Record>();
            for (Record recordList2RecordToAttach : employee.getRecordList2()) {
                recordList2RecordToAttach = em.getReference(recordList2RecordToAttach.getClass(), recordList2RecordToAttach.getId());
                attachedRecordList2.add(recordList2RecordToAttach);
            }
            employee.setRecordList2(attachedRecordList2);
            List<Invoice> attachedInvoiceList = new ArrayList<Invoice>();
            for (Invoice invoiceListInvoiceToAttach : employee.getInvoiceList()) {
                invoiceListInvoiceToAttach = em.getReference(invoiceListInvoiceToAttach.getClass(), invoiceListInvoiceToAttach.getId());
                attachedInvoiceList.add(invoiceListInvoiceToAttach);
            }
            employee.setInvoiceList(attachedInvoiceList);
            List<Invoice> attachedInvoiceList1 = new ArrayList<Invoice>();
            for (Invoice invoiceList1InvoiceToAttach : employee.getInvoiceList1()) {
                invoiceList1InvoiceToAttach = em.getReference(invoiceList1InvoiceToAttach.getClass(), invoiceList1InvoiceToAttach.getId());
                attachedInvoiceList1.add(invoiceList1InvoiceToAttach);
            }
            employee.setInvoiceList1(attachedInvoiceList1);
            List<Invoice> attachedInvoiceList2 = new ArrayList<Invoice>();
            for (Invoice invoiceList2InvoiceToAttach : employee.getInvoiceList2()) {
                invoiceList2InvoiceToAttach = em.getReference(invoiceList2InvoiceToAttach.getClass(), invoiceList2InvoiceToAttach.getId());
                attachedInvoiceList2.add(invoiceList2InvoiceToAttach);
            }
            employee.setInvoiceList2(attachedInvoiceList2);
            List<Client> attachedClientList = new ArrayList<Client>();
            for (Client clientListClientToAttach : employee.getClientList()) {
                clientListClientToAttach = em.getReference(clientListClientToAttach.getClass(), clientListClientToAttach.getId());
                attachedClientList.add(clientListClientToAttach);
            }
            employee.setClientList(attachedClientList);
            List<Client> attachedClientList1 = new ArrayList<Client>();
            for (Client clientList1ClientToAttach : employee.getClientList1()) {
                clientList1ClientToAttach = em.getReference(clientList1ClientToAttach.getClass(), clientList1ClientToAttach.getId());
                attachedClientList1.add(clientList1ClientToAttach);
            }
            employee.setClientList1(attachedClientList1);
            List<Stock> attachedStockList = new ArrayList<Stock>();
            for (Stock stockListStockToAttach : employee.getStockList()) {
                stockListStockToAttach = em.getReference(stockListStockToAttach.getClass(), stockListStockToAttach.getId());
                attachedStockList.add(stockListStockToAttach);
            }
            employee.setStockList(attachedStockList);
            List<Stock> attachedStockList1 = new ArrayList<Stock>();
            for (Stock stockList1StockToAttach : employee.getStockList1()) {
                stockList1StockToAttach = em.getReference(stockList1StockToAttach.getClass(), stockList1StockToAttach.getId());
                attachedStockList1.add(stockList1StockToAttach);
            }
            employee.setStockList1(attachedStockList1);
            List<Brand> attachedBrandList = new ArrayList<Brand>();
            for (Brand brandListBrandToAttach : employee.getBrandList()) {
                brandListBrandToAttach = em.getReference(brandListBrandToAttach.getClass(), brandListBrandToAttach.getId());
                attachedBrandList.add(brandListBrandToAttach);
            }
            employee.setBrandList(attachedBrandList);
            List<Brand> attachedBrandList1 = new ArrayList<Brand>();
            for (Brand brandList1BrandToAttach : employee.getBrandList1()) {
                brandList1BrandToAttach = em.getReference(brandList1BrandToAttach.getClass(), brandList1BrandToAttach.getId());
                attachedBrandList1.add(brandList1BrandToAttach);
            }
            employee.setBrandList1(attachedBrandList1);
            List<SexType> attachedSexTypeList = new ArrayList<SexType>();
            for (SexType sexTypeListSexTypeToAttach : employee.getSexTypeList()) {
                sexTypeListSexTypeToAttach = em.getReference(sexTypeListSexTypeToAttach.getClass(), sexTypeListSexTypeToAttach.getId());
                attachedSexTypeList.add(sexTypeListSexTypeToAttach);
            }
            employee.setSexTypeList(attachedSexTypeList);
            List<SexType> attachedSexTypeList1 = new ArrayList<SexType>();
            for (SexType sexTypeList1SexTypeToAttach : employee.getSexTypeList1()) {
                sexTypeList1SexTypeToAttach = em.getReference(sexTypeList1SexTypeToAttach.getClass(), sexTypeList1SexTypeToAttach.getId());
                attachedSexTypeList1.add(sexTypeList1SexTypeToAttach);
            }
            employee.setSexTypeList1(attachedSexTypeList1);
            List<PaymentType> attachedPaymentTypeList = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeListPaymentTypeToAttach : employee.getPaymentTypeList()) {
                paymentTypeListPaymentTypeToAttach = em.getReference(paymentTypeListPaymentTypeToAttach.getClass(), paymentTypeListPaymentTypeToAttach.getId());
                attachedPaymentTypeList.add(paymentTypeListPaymentTypeToAttach);
            }
            employee.setPaymentTypeList(attachedPaymentTypeList);
            List<PaymentType> attachedPaymentTypeList1 = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeList1PaymentTypeToAttach : employee.getPaymentTypeList1()) {
                paymentTypeList1PaymentTypeToAttach = em.getReference(paymentTypeList1PaymentTypeToAttach.getClass(), paymentTypeList1PaymentTypeToAttach.getId());
                attachedPaymentTypeList1.add(paymentTypeList1PaymentTypeToAttach);
            }
            employee.setPaymentTypeList1(attachedPaymentTypeList1);
            List<EmployeeType> attachedEmployeeTypeList = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListEmployeeTypeToAttach : employee.getEmployeeTypeList()) {
                employeeTypeListEmployeeTypeToAttach = em.getReference(employeeTypeListEmployeeTypeToAttach.getClass(), employeeTypeListEmployeeTypeToAttach.getId());
                attachedEmployeeTypeList.add(employeeTypeListEmployeeTypeToAttach);
            }
            employee.setEmployeeTypeList(attachedEmployeeTypeList);
            List<EmployeeType> attachedEmployeeTypeList1 = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeList1EmployeeTypeToAttach : employee.getEmployeeTypeList1()) {
                employeeTypeList1EmployeeTypeToAttach = em.getReference(employeeTypeList1EmployeeTypeToAttach.getClass(), employeeTypeList1EmployeeTypeToAttach.getId());
                attachedEmployeeTypeList1.add(employeeTypeList1EmployeeTypeToAttach);
            }
            employee.setEmployeeTypeList1(attachedEmployeeTypeList1);
            List<Item> attachedItemList = new ArrayList<Item>();
            for (Item itemListItemToAttach : employee.getItemList()) {
                itemListItemToAttach = em.getReference(itemListItemToAttach.getClass(), itemListItemToAttach.getId());
                attachedItemList.add(itemListItemToAttach);
            }
            employee.setItemList(attachedItemList);
            List<Item> attachedItemList1 = new ArrayList<Item>();
            for (Item itemList1ItemToAttach : employee.getItemList1()) {
                itemList1ItemToAttach = em.getReference(itemList1ItemToAttach.getClass(), itemList1ItemToAttach.getId());
                attachedItemList1.add(itemList1ItemToAttach);
            }
            employee.setItemList1(attachedItemList1);
            List<ProductType> attachedProductTypeList = new ArrayList<ProductType>();
            for (ProductType productTypeListProductTypeToAttach : employee.getProductTypeList()) {
                productTypeListProductTypeToAttach = em.getReference(productTypeListProductTypeToAttach.getClass(), productTypeListProductTypeToAttach.getId());
                attachedProductTypeList.add(productTypeListProductTypeToAttach);
            }
            employee.setProductTypeList(attachedProductTypeList);
            List<ProductType> attachedProductTypeList1 = new ArrayList<ProductType>();
            for (ProductType productTypeList1ProductTypeToAttach : employee.getProductTypeList1()) {
                productTypeList1ProductTypeToAttach = em.getReference(productTypeList1ProductTypeToAttach.getClass(), productTypeList1ProductTypeToAttach.getId());
                attachedProductTypeList1.add(productTypeList1ProductTypeToAttach);
            }
            employee.setProductTypeList1(attachedProductTypeList1);
            List<AnalyticsTime> attachedAnalyticsTimeList = new ArrayList<AnalyticsTime>();
            for (AnalyticsTime analyticsTimeListAnalyticsTimeToAttach : employee.getAnalyticsTimeList()) {
                analyticsTimeListAnalyticsTimeToAttach = em.getReference(analyticsTimeListAnalyticsTimeToAttach.getClass(), analyticsTimeListAnalyticsTimeToAttach.getId());
                attachedAnalyticsTimeList.add(analyticsTimeListAnalyticsTimeToAttach);
            }
            employee.setAnalyticsTimeList(attachedAnalyticsTimeList);
            List<AnalyticsTime> attachedAnalyticsTimeList1 = new ArrayList<AnalyticsTime>();
            for (AnalyticsTime analyticsTimeList1AnalyticsTimeToAttach : employee.getAnalyticsTimeList1()) {
                analyticsTimeList1AnalyticsTimeToAttach = em.getReference(analyticsTimeList1AnalyticsTimeToAttach.getClass(), analyticsTimeList1AnalyticsTimeToAttach.getId());
                attachedAnalyticsTimeList1.add(analyticsTimeList1AnalyticsTimeToAttach);
            }
            employee.setAnalyticsTimeList1(attachedAnalyticsTimeList1);
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : employee.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getId());
                attachedProductList.add(productListProductToAttach);
            }
            employee.setProductList(attachedProductList);
            List<Product> attachedProductList1 = new ArrayList<Product>();
            for (Product productList1ProductToAttach : employee.getProductList1()) {
                productList1ProductToAttach = em.getReference(productList1ProductToAttach.getClass(), productList1ProductToAttach.getId());
                attachedProductList1.add(productList1ProductToAttach);
            }
            employee.setProductList1(attachedProductList1);
            List<Provider> attachedProviderList = new ArrayList<Provider>();
            for (Provider providerListProviderToAttach : employee.getProviderList()) {
                providerListProviderToAttach = em.getReference(providerListProviderToAttach.getClass(), providerListProviderToAttach.getId());
                attachedProviderList.add(providerListProviderToAttach);
            }
            employee.setProviderList(attachedProviderList);
            List<Provider> attachedProviderList1 = new ArrayList<Provider>();
            for (Provider providerList1ProviderToAttach : employee.getProviderList1()) {
                providerList1ProviderToAttach = em.getReference(providerList1ProviderToAttach.getClass(), providerList1ProviderToAttach.getId());
                attachedProviderList1.add(providerList1ProviderToAttach);
            }
            employee.setProviderList1(attachedProviderList1);
            List<Store> attachedStoreList = new ArrayList<Store>();
            for (Store storeListStoreToAttach : employee.getStoreList()) {
                storeListStoreToAttach = em.getReference(storeListStoreToAttach.getClass(), storeListStoreToAttach.getId());
                attachedStoreList.add(storeListStoreToAttach);
            }
            employee.setStoreList(attachedStoreList);
            List<Store> attachedStoreList1 = new ArrayList<Store>();
            for (Store storeList1StoreToAttach : employee.getStoreList1()) {
                storeList1StoreToAttach = em.getReference(storeList1StoreToAttach.getClass(), storeList1StoreToAttach.getId());
                attachedStoreList1.add(storeList1StoreToAttach);
            }
            employee.setStoreList1(attachedStoreList1);
            List<Store> attachedStoreList2 = new ArrayList<Store>();
            for (Store storeList2StoreToAttach : employee.getStoreList2()) {
                storeList2StoreToAttach = em.getReference(storeList2StoreToAttach.getClass(), storeList2StoreToAttach.getId());
                attachedStoreList2.add(storeList2StoreToAttach);
            }
            employee.setStoreList2(attachedStoreList2);
            em.persist(employee);
            if (createdUser != null) {
                createdUser.getEmployeeList().add(employee);
                createdUser = em.merge(createdUser);
            }
            if (employeeType != null) {
                employeeType.getEmployeeList().add(employee);
                employeeType = em.merge(employeeType);
            }
            if (sexType != null) {
                sexType.getEmployeeList().add(employee);
                sexType = em.merge(sexType);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getEmployeeList().add(employee);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            if (languageType != null) {
                languageType.getEmployeeList().add(employee);
                languageType = em.merge(languageType);
            }
            for (Price priceListPrice : employee.getPriceList()) {
                Employee oldCreatedUserOfPriceListPrice = priceListPrice.getCreatedUser();
                priceListPrice.setCreatedUser(employee);
                priceListPrice = em.merge(priceListPrice);
                if (oldCreatedUserOfPriceListPrice != null) {
                    oldCreatedUserOfPriceListPrice.getPriceList().remove(priceListPrice);
                    oldCreatedUserOfPriceListPrice = em.merge(oldCreatedUserOfPriceListPrice);
                }
            }
            for (Price priceList1Price : employee.getPriceList1()) {
                Employee oldLastModifiedUserOfPriceList1Price = priceList1Price.getLastModifiedUser();
                priceList1Price.setLastModifiedUser(employee);
                priceList1Price = em.merge(priceList1Price);
                if (oldLastModifiedUserOfPriceList1Price != null) {
                    oldLastModifiedUserOfPriceList1Price.getPriceList1().remove(priceList1Price);
                    oldLastModifiedUserOfPriceList1Price = em.merge(oldLastModifiedUserOfPriceList1Price);
                }
            }
            for (Employee employeeListEmployee : employee.getEmployeeList()) {
                Employee oldCreatedUserOfEmployeeListEmployee = employeeListEmployee.getCreatedUser();
                employeeListEmployee.setCreatedUser(employee);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldCreatedUserOfEmployeeListEmployee != null) {
                    oldCreatedUserOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldCreatedUserOfEmployeeListEmployee = em.merge(oldCreatedUserOfEmployeeListEmployee);
                }
            }
            for (Employee employeeList1Employee : employee.getEmployeeList1()) {
                Employee oldLastModifiedUserOfEmployeeList1Employee = employeeList1Employee.getLastModifiedUser();
                employeeList1Employee.setLastModifiedUser(employee);
                employeeList1Employee = em.merge(employeeList1Employee);
                if (oldLastModifiedUserOfEmployeeList1Employee != null) {
                    oldLastModifiedUserOfEmployeeList1Employee.getEmployeeList1().remove(employeeList1Employee);
                    oldLastModifiedUserOfEmployeeList1Employee = em.merge(oldLastModifiedUserOfEmployeeList1Employee);
                }
            }
            for (PriceType priceTypeListPriceType : employee.getPriceTypeList()) {
                Employee oldCreatedUserOfPriceTypeListPriceType = priceTypeListPriceType.getCreatedUser();
                priceTypeListPriceType.setCreatedUser(employee);
                priceTypeListPriceType = em.merge(priceTypeListPriceType);
                if (oldCreatedUserOfPriceTypeListPriceType != null) {
                    oldCreatedUserOfPriceTypeListPriceType.getPriceTypeList().remove(priceTypeListPriceType);
                    oldCreatedUserOfPriceTypeListPriceType = em.merge(oldCreatedUserOfPriceTypeListPriceType);
                }
            }
            for (PriceType priceTypeList1PriceType : employee.getPriceTypeList1()) {
                Employee oldLastModifiedUserOfPriceTypeList1PriceType = priceTypeList1PriceType.getLastModifiedUser();
                priceTypeList1PriceType.setLastModifiedUser(employee);
                priceTypeList1PriceType = em.merge(priceTypeList1PriceType);
                if (oldLastModifiedUserOfPriceTypeList1PriceType != null) {
                    oldLastModifiedUserOfPriceTypeList1PriceType.getPriceTypeList1().remove(priceTypeList1PriceType);
                    oldLastModifiedUserOfPriceTypeList1PriceType = em.merge(oldLastModifiedUserOfPriceTypeList1PriceType);
                }
            }
            for (Record recordListRecord : employee.getRecordList()) {
                Employee oldCreatedUserOfRecordListRecord = recordListRecord.getCreatedUser();
                recordListRecord.setCreatedUser(employee);
                recordListRecord = em.merge(recordListRecord);
                if (oldCreatedUserOfRecordListRecord != null) {
                    oldCreatedUserOfRecordListRecord.getRecordList().remove(recordListRecord);
                    oldCreatedUserOfRecordListRecord = em.merge(oldCreatedUserOfRecordListRecord);
                }
            }
            for (Record recordList1Record : employee.getRecordList1()) {
                Employee oldEmployeeOfRecordList1Record = recordList1Record.getEmployee();
                recordList1Record.setEmployee(employee);
                recordList1Record = em.merge(recordList1Record);
                if (oldEmployeeOfRecordList1Record != null) {
                    oldEmployeeOfRecordList1Record.getRecordList1().remove(recordList1Record);
                    oldEmployeeOfRecordList1Record = em.merge(oldEmployeeOfRecordList1Record);
                }
            }
            for (Record recordList2Record : employee.getRecordList2()) {
                Employee oldLastModifiedUserOfRecordList2Record = recordList2Record.getLastModifiedUser();
                recordList2Record.setLastModifiedUser(employee);
                recordList2Record = em.merge(recordList2Record);
                if (oldLastModifiedUserOfRecordList2Record != null) {
                    oldLastModifiedUserOfRecordList2Record.getRecordList2().remove(recordList2Record);
                    oldLastModifiedUserOfRecordList2Record = em.merge(oldLastModifiedUserOfRecordList2Record);
                }
            }
            for (Invoice invoiceListInvoice : employee.getInvoiceList()) {
                Employee oldCreatedUserOfInvoiceListInvoice = invoiceListInvoice.getCreatedUser();
                invoiceListInvoice.setCreatedUser(employee);
                invoiceListInvoice = em.merge(invoiceListInvoice);
                if (oldCreatedUserOfInvoiceListInvoice != null) {
                    oldCreatedUserOfInvoiceListInvoice.getInvoiceList().remove(invoiceListInvoice);
                    oldCreatedUserOfInvoiceListInvoice = em.merge(oldCreatedUserOfInvoiceListInvoice);
                }
            }
            for (Invoice invoiceList1Invoice : employee.getInvoiceList1()) {
                Employee oldEmployeeOfInvoiceList1Invoice = invoiceList1Invoice.getEmployee();
                invoiceList1Invoice.setEmployee(employee);
                invoiceList1Invoice = em.merge(invoiceList1Invoice);
                if (oldEmployeeOfInvoiceList1Invoice != null) {
                    oldEmployeeOfInvoiceList1Invoice.getInvoiceList1().remove(invoiceList1Invoice);
                    oldEmployeeOfInvoiceList1Invoice = em.merge(oldEmployeeOfInvoiceList1Invoice);
                }
            }
            for (Invoice invoiceList2Invoice : employee.getInvoiceList2()) {
                Employee oldLastModifiedUserOfInvoiceList2Invoice = invoiceList2Invoice.getLastModifiedUser();
                invoiceList2Invoice.setLastModifiedUser(employee);
                invoiceList2Invoice = em.merge(invoiceList2Invoice);
                if (oldLastModifiedUserOfInvoiceList2Invoice != null) {
                    oldLastModifiedUserOfInvoiceList2Invoice.getInvoiceList2().remove(invoiceList2Invoice);
                    oldLastModifiedUserOfInvoiceList2Invoice = em.merge(oldLastModifiedUserOfInvoiceList2Invoice);
                }
            }
            for (Client clientListClient : employee.getClientList()) {
                Employee oldCreatedUserOfClientListClient = clientListClient.getCreatedUser();
                clientListClient.setCreatedUser(employee);
                clientListClient = em.merge(clientListClient);
                if (oldCreatedUserOfClientListClient != null) {
                    oldCreatedUserOfClientListClient.getClientList().remove(clientListClient);
                    oldCreatedUserOfClientListClient = em.merge(oldCreatedUserOfClientListClient);
                }
            }
            for (Client clientList1Client : employee.getClientList1()) {
                Employee oldLastModifiedUserOfClientList1Client = clientList1Client.getLastModifiedUser();
                clientList1Client.setLastModifiedUser(employee);
                clientList1Client = em.merge(clientList1Client);
                if (oldLastModifiedUserOfClientList1Client != null) {
                    oldLastModifiedUserOfClientList1Client.getClientList1().remove(clientList1Client);
                    oldLastModifiedUserOfClientList1Client = em.merge(oldLastModifiedUserOfClientList1Client);
                }
            }
            for (Stock stockListStock : employee.getStockList()) {
                Employee oldCreatedUserOfStockListStock = stockListStock.getCreatedUser();
                stockListStock.setCreatedUser(employee);
                stockListStock = em.merge(stockListStock);
                if (oldCreatedUserOfStockListStock != null) {
                    oldCreatedUserOfStockListStock.getStockList().remove(stockListStock);
                    oldCreatedUserOfStockListStock = em.merge(oldCreatedUserOfStockListStock);
                }
            }
            for (Stock stockList1Stock : employee.getStockList1()) {
                Employee oldLastModifiedUserOfStockList1Stock = stockList1Stock.getLastModifiedUser();
                stockList1Stock.setLastModifiedUser(employee);
                stockList1Stock = em.merge(stockList1Stock);
                if (oldLastModifiedUserOfStockList1Stock != null) {
                    oldLastModifiedUserOfStockList1Stock.getStockList1().remove(stockList1Stock);
                    oldLastModifiedUserOfStockList1Stock = em.merge(oldLastModifiedUserOfStockList1Stock);
                }
            }
            for (Brand brandListBrand : employee.getBrandList()) {
                Employee oldCreatedUserOfBrandListBrand = brandListBrand.getCreatedUser();
                brandListBrand.setCreatedUser(employee);
                brandListBrand = em.merge(brandListBrand);
                if (oldCreatedUserOfBrandListBrand != null) {
                    oldCreatedUserOfBrandListBrand.getBrandList().remove(brandListBrand);
                    oldCreatedUserOfBrandListBrand = em.merge(oldCreatedUserOfBrandListBrand);
                }
            }
            for (Brand brandList1Brand : employee.getBrandList1()) {
                Employee oldLastModifiedUserOfBrandList1Brand = brandList1Brand.getLastModifiedUser();
                brandList1Brand.setLastModifiedUser(employee);
                brandList1Brand = em.merge(brandList1Brand);
                if (oldLastModifiedUserOfBrandList1Brand != null) {
                    oldLastModifiedUserOfBrandList1Brand.getBrandList1().remove(brandList1Brand);
                    oldLastModifiedUserOfBrandList1Brand = em.merge(oldLastModifiedUserOfBrandList1Brand);
                }
            }
            for (SexType sexTypeListSexType : employee.getSexTypeList()) {
                Employee oldCreatedUserOfSexTypeListSexType = sexTypeListSexType.getCreatedUser();
                sexTypeListSexType.setCreatedUser(employee);
                sexTypeListSexType = em.merge(sexTypeListSexType);
                if (oldCreatedUserOfSexTypeListSexType != null) {
                    oldCreatedUserOfSexTypeListSexType.getSexTypeList().remove(sexTypeListSexType);
                    oldCreatedUserOfSexTypeListSexType = em.merge(oldCreatedUserOfSexTypeListSexType);
                }
            }
            for (SexType sexTypeList1SexType : employee.getSexTypeList1()) {
                Employee oldLastModifiedUserOfSexTypeList1SexType = sexTypeList1SexType.getLastModifiedUser();
                sexTypeList1SexType.setLastModifiedUser(employee);
                sexTypeList1SexType = em.merge(sexTypeList1SexType);
                if (oldLastModifiedUserOfSexTypeList1SexType != null) {
                    oldLastModifiedUserOfSexTypeList1SexType.getSexTypeList1().remove(sexTypeList1SexType);
                    oldLastModifiedUserOfSexTypeList1SexType = em.merge(oldLastModifiedUserOfSexTypeList1SexType);
                }
            }
            for (PaymentType paymentTypeListPaymentType : employee.getPaymentTypeList()) {
                Employee oldCreatedUserOfPaymentTypeListPaymentType = paymentTypeListPaymentType.getCreatedUser();
                paymentTypeListPaymentType.setCreatedUser(employee);
                paymentTypeListPaymentType = em.merge(paymentTypeListPaymentType);
                if (oldCreatedUserOfPaymentTypeListPaymentType != null) {
                    oldCreatedUserOfPaymentTypeListPaymentType.getPaymentTypeList().remove(paymentTypeListPaymentType);
                    oldCreatedUserOfPaymentTypeListPaymentType = em.merge(oldCreatedUserOfPaymentTypeListPaymentType);
                }
            }
            for (PaymentType paymentTypeList1PaymentType : employee.getPaymentTypeList1()) {
                Employee oldLastModifiedUserOfPaymentTypeList1PaymentType = paymentTypeList1PaymentType.getLastModifiedUser();
                paymentTypeList1PaymentType.setLastModifiedUser(employee);
                paymentTypeList1PaymentType = em.merge(paymentTypeList1PaymentType);
                if (oldLastModifiedUserOfPaymentTypeList1PaymentType != null) {
                    oldLastModifiedUserOfPaymentTypeList1PaymentType.getPaymentTypeList1().remove(paymentTypeList1PaymentType);
                    oldLastModifiedUserOfPaymentTypeList1PaymentType = em.merge(oldLastModifiedUserOfPaymentTypeList1PaymentType);
                }
            }
            for (EmployeeType employeeTypeListEmployeeType : employee.getEmployeeTypeList()) {
                Employee oldCreatedUserOfEmployeeTypeListEmployeeType = employeeTypeListEmployeeType.getCreatedUser();
                employeeTypeListEmployeeType.setCreatedUser(employee);
                employeeTypeListEmployeeType = em.merge(employeeTypeListEmployeeType);
                if (oldCreatedUserOfEmployeeTypeListEmployeeType != null) {
                    oldCreatedUserOfEmployeeTypeListEmployeeType.getEmployeeTypeList().remove(employeeTypeListEmployeeType);
                    oldCreatedUserOfEmployeeTypeListEmployeeType = em.merge(oldCreatedUserOfEmployeeTypeListEmployeeType);
                }
            }
            for (EmployeeType employeeTypeList1EmployeeType : employee.getEmployeeTypeList1()) {
                Employee oldLastModifiedUserOfEmployeeTypeList1EmployeeType = employeeTypeList1EmployeeType.getLastModifiedUser();
                employeeTypeList1EmployeeType.setLastModifiedUser(employee);
                employeeTypeList1EmployeeType = em.merge(employeeTypeList1EmployeeType);
                if (oldLastModifiedUserOfEmployeeTypeList1EmployeeType != null) {
                    oldLastModifiedUserOfEmployeeTypeList1EmployeeType.getEmployeeTypeList1().remove(employeeTypeList1EmployeeType);
                    oldLastModifiedUserOfEmployeeTypeList1EmployeeType = em.merge(oldLastModifiedUserOfEmployeeTypeList1EmployeeType);
                }
            }
            for (Item itemListItem : employee.getItemList()) {
                Employee oldCreatedUserOfItemListItem = itemListItem.getCreatedUser();
                itemListItem.setCreatedUser(employee);
                itemListItem = em.merge(itemListItem);
                if (oldCreatedUserOfItemListItem != null) {
                    oldCreatedUserOfItemListItem.getItemList().remove(itemListItem);
                    oldCreatedUserOfItemListItem = em.merge(oldCreatedUserOfItemListItem);
                }
            }
            for (Item itemList1Item : employee.getItemList1()) {
                Employee oldLastModifiedUserOfItemList1Item = itemList1Item.getLastModifiedUser();
                itemList1Item.setLastModifiedUser(employee);
                itemList1Item = em.merge(itemList1Item);
                if (oldLastModifiedUserOfItemList1Item != null) {
                    oldLastModifiedUserOfItemList1Item.getItemList1().remove(itemList1Item);
                    oldLastModifiedUserOfItemList1Item = em.merge(oldLastModifiedUserOfItemList1Item);
                }
            }
            for (ProductType productTypeListProductType : employee.getProductTypeList()) {
                Employee oldCreatedUserOfProductTypeListProductType = productTypeListProductType.getCreatedUser();
                productTypeListProductType.setCreatedUser(employee);
                productTypeListProductType = em.merge(productTypeListProductType);
                if (oldCreatedUserOfProductTypeListProductType != null) {
                    oldCreatedUserOfProductTypeListProductType.getProductTypeList().remove(productTypeListProductType);
                    oldCreatedUserOfProductTypeListProductType = em.merge(oldCreatedUserOfProductTypeListProductType);
                }
            }
            for (ProductType productTypeList1ProductType : employee.getProductTypeList1()) {
                Employee oldLastModifiedUserOfProductTypeList1ProductType = productTypeList1ProductType.getLastModifiedUser();
                productTypeList1ProductType.setLastModifiedUser(employee);
                productTypeList1ProductType = em.merge(productTypeList1ProductType);
                if (oldLastModifiedUserOfProductTypeList1ProductType != null) {
                    oldLastModifiedUserOfProductTypeList1ProductType.getProductTypeList1().remove(productTypeList1ProductType);
                    oldLastModifiedUserOfProductTypeList1ProductType = em.merge(oldLastModifiedUserOfProductTypeList1ProductType);
                }
            }
            for (AnalyticsTime analyticsTimeListAnalyticsTime : employee.getAnalyticsTimeList()) {
                Employee oldCreatedUserOfAnalyticsTimeListAnalyticsTime = analyticsTimeListAnalyticsTime.getCreatedUser();
                analyticsTimeListAnalyticsTime.setCreatedUser(employee);
                analyticsTimeListAnalyticsTime = em.merge(analyticsTimeListAnalyticsTime);
                if (oldCreatedUserOfAnalyticsTimeListAnalyticsTime != null) {
                    oldCreatedUserOfAnalyticsTimeListAnalyticsTime.getAnalyticsTimeList().remove(analyticsTimeListAnalyticsTime);
                    oldCreatedUserOfAnalyticsTimeListAnalyticsTime = em.merge(oldCreatedUserOfAnalyticsTimeListAnalyticsTime);
                }
            }
            for (AnalyticsTime analyticsTimeList1AnalyticsTime : employee.getAnalyticsTimeList1()) {
                Employee oldLastModifiedUserOfAnalyticsTimeList1AnalyticsTime = analyticsTimeList1AnalyticsTime.getLastModifiedUser();
                analyticsTimeList1AnalyticsTime.setLastModifiedUser(employee);
                analyticsTimeList1AnalyticsTime = em.merge(analyticsTimeList1AnalyticsTime);
                if (oldLastModifiedUserOfAnalyticsTimeList1AnalyticsTime != null) {
                    oldLastModifiedUserOfAnalyticsTimeList1AnalyticsTime.getAnalyticsTimeList1().remove(analyticsTimeList1AnalyticsTime);
                    oldLastModifiedUserOfAnalyticsTimeList1AnalyticsTime = em.merge(oldLastModifiedUserOfAnalyticsTimeList1AnalyticsTime);
                }
            }
            for (Product productListProduct : employee.getProductList()) {
                Employee oldCreatedUserOfProductListProduct = productListProduct.getCreatedUser();
                productListProduct.setCreatedUser(employee);
                productListProduct = em.merge(productListProduct);
                if (oldCreatedUserOfProductListProduct != null) {
                    oldCreatedUserOfProductListProduct.getProductList().remove(productListProduct);
                    oldCreatedUserOfProductListProduct = em.merge(oldCreatedUserOfProductListProduct);
                }
            }
            for (Product productList1Product : employee.getProductList1()) {
                Employee oldLastModifiedUserOfProductList1Product = productList1Product.getLastModifiedUser();
                productList1Product.setLastModifiedUser(employee);
                productList1Product = em.merge(productList1Product);
                if (oldLastModifiedUserOfProductList1Product != null) {
                    oldLastModifiedUserOfProductList1Product.getProductList1().remove(productList1Product);
                    oldLastModifiedUserOfProductList1Product = em.merge(oldLastModifiedUserOfProductList1Product);
                }
            }
            for (Provider providerListProvider : employee.getProviderList()) {
                Employee oldCreatedUserOfProviderListProvider = providerListProvider.getCreatedUser();
                providerListProvider.setCreatedUser(employee);
                providerListProvider = em.merge(providerListProvider);
                if (oldCreatedUserOfProviderListProvider != null) {
                    oldCreatedUserOfProviderListProvider.getProviderList().remove(providerListProvider);
                    oldCreatedUserOfProviderListProvider = em.merge(oldCreatedUserOfProviderListProvider);
                }
            }
            for (Provider providerList1Provider : employee.getProviderList1()) {
                Employee oldLastModifiedUserOfProviderList1Provider = providerList1Provider.getLastModifiedUser();
                providerList1Provider.setLastModifiedUser(employee);
                providerList1Provider = em.merge(providerList1Provider);
                if (oldLastModifiedUserOfProviderList1Provider != null) {
                    oldLastModifiedUserOfProviderList1Provider.getProviderList1().remove(providerList1Provider);
                    oldLastModifiedUserOfProviderList1Provider = em.merge(oldLastModifiedUserOfProviderList1Provider);
                }
            }
            for (Store storeListStore : employee.getStoreList()) {
                Employee oldCreatedUserOfStoreListStore = storeListStore.getCreatedUser();
                storeListStore.setCreatedUser(employee);
                storeListStore = em.merge(storeListStore);
                if (oldCreatedUserOfStoreListStore != null) {
                    oldCreatedUserOfStoreListStore.getStoreList().remove(storeListStore);
                    oldCreatedUserOfStoreListStore = em.merge(oldCreatedUserOfStoreListStore);
                }
            }
            for (Store storeList1Store : employee.getStoreList1()) {
                Employee oldEmployeeOfStoreList1Store = storeList1Store.getEmployee();
                storeList1Store.setEmployee(employee);
                storeList1Store = em.merge(storeList1Store);
                if (oldEmployeeOfStoreList1Store != null) {
                    oldEmployeeOfStoreList1Store.getStoreList1().remove(storeList1Store);
                    oldEmployeeOfStoreList1Store = em.merge(oldEmployeeOfStoreList1Store);
                }
            }
            for (Store storeList2Store : employee.getStoreList2()) {
                Employee oldLastModifiedUserOfStoreList2Store = storeList2Store.getLastModifiedUser();
                storeList2Store.setLastModifiedUser(employee);
                storeList2Store = em.merge(storeList2Store);
                if (oldLastModifiedUserOfStoreList2Store != null) {
                    oldLastModifiedUserOfStoreList2Store.getStoreList2().remove(storeList2Store);
                    oldLastModifiedUserOfStoreList2Store = em.merge(oldLastModifiedUserOfStoreList2Store);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employee employee) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee persistentEmployee = em.find(Employee.class, employee.getId());
            Employee createdUserOld = persistentEmployee.getCreatedUser();
            Employee createdUserNew = employee.getCreatedUser();
            EmployeeType employeeTypeOld = persistentEmployee.getEmployeeType();
            EmployeeType employeeTypeNew = employee.getEmployeeType();
            SexType sexTypeOld = persistentEmployee.getSexType();
            SexType sexTypeNew = employee.getSexType();
            Employee lastModifiedUserOld = persistentEmployee.getLastModifiedUser();
            Employee lastModifiedUserNew = employee.getLastModifiedUser();
            Language languageTypeOld = persistentEmployee.getLanguageType();
            Language languageTypeNew = employee.getLanguageType();
            List<Price> priceListOld = persistentEmployee.getPriceList();
            List<Price> priceListNew = employee.getPriceList();
            List<Price> priceList1Old = persistentEmployee.getPriceList1();
            List<Price> priceList1New = employee.getPriceList1();
            List<Employee> employeeListOld = persistentEmployee.getEmployeeList();
            List<Employee> employeeListNew = employee.getEmployeeList();
            List<Employee> employeeList1Old = persistentEmployee.getEmployeeList1();
            List<Employee> employeeList1New = employee.getEmployeeList1();
            List<PriceType> priceTypeListOld = persistentEmployee.getPriceTypeList();
            List<PriceType> priceTypeListNew = employee.getPriceTypeList();
            List<PriceType> priceTypeList1Old = persistentEmployee.getPriceTypeList1();
            List<PriceType> priceTypeList1New = employee.getPriceTypeList1();
            List<Record> recordListOld = persistentEmployee.getRecordList();
            List<Record> recordListNew = employee.getRecordList();
            List<Record> recordList1Old = persistentEmployee.getRecordList1();
            List<Record> recordList1New = employee.getRecordList1();
            List<Record> recordList2Old = persistentEmployee.getRecordList2();
            List<Record> recordList2New = employee.getRecordList2();
            List<Invoice> invoiceListOld = persistentEmployee.getInvoiceList();
            List<Invoice> invoiceListNew = employee.getInvoiceList();
            List<Invoice> invoiceList1Old = persistentEmployee.getInvoiceList1();
            List<Invoice> invoiceList1New = employee.getInvoiceList1();
            List<Invoice> invoiceList2Old = persistentEmployee.getInvoiceList2();
            List<Invoice> invoiceList2New = employee.getInvoiceList2();
            List<Client> clientListOld = persistentEmployee.getClientList();
            List<Client> clientListNew = employee.getClientList();
            List<Client> clientList1Old = persistentEmployee.getClientList1();
            List<Client> clientList1New = employee.getClientList1();
            List<Stock> stockListOld = persistentEmployee.getStockList();
            List<Stock> stockListNew = employee.getStockList();
            List<Stock> stockList1Old = persistentEmployee.getStockList1();
            List<Stock> stockList1New = employee.getStockList1();
            List<Brand> brandListOld = persistentEmployee.getBrandList();
            List<Brand> brandListNew = employee.getBrandList();
            List<Brand> brandList1Old = persistentEmployee.getBrandList1();
            List<Brand> brandList1New = employee.getBrandList1();
            List<SexType> sexTypeListOld = persistentEmployee.getSexTypeList();
            List<SexType> sexTypeListNew = employee.getSexTypeList();
            List<SexType> sexTypeList1Old = persistentEmployee.getSexTypeList1();
            List<SexType> sexTypeList1New = employee.getSexTypeList1();
            List<PaymentType> paymentTypeListOld = persistentEmployee.getPaymentTypeList();
            List<PaymentType> paymentTypeListNew = employee.getPaymentTypeList();
            List<PaymentType> paymentTypeList1Old = persistentEmployee.getPaymentTypeList1();
            List<PaymentType> paymentTypeList1New = employee.getPaymentTypeList1();
            List<EmployeeType> employeeTypeListOld = persistentEmployee.getEmployeeTypeList();
            List<EmployeeType> employeeTypeListNew = employee.getEmployeeTypeList();
            List<EmployeeType> employeeTypeList1Old = persistentEmployee.getEmployeeTypeList1();
            List<EmployeeType> employeeTypeList1New = employee.getEmployeeTypeList1();
            List<Item> itemListOld = persistentEmployee.getItemList();
            List<Item> itemListNew = employee.getItemList();
            List<Item> itemList1Old = persistentEmployee.getItemList1();
            List<Item> itemList1New = employee.getItemList1();
            List<ProductType> productTypeListOld = persistentEmployee.getProductTypeList();
            List<ProductType> productTypeListNew = employee.getProductTypeList();
            List<ProductType> productTypeList1Old = persistentEmployee.getProductTypeList1();
            List<ProductType> productTypeList1New = employee.getProductTypeList1();
            List<AnalyticsTime> analyticsTimeListOld = persistentEmployee.getAnalyticsTimeList();
            List<AnalyticsTime> analyticsTimeListNew = employee.getAnalyticsTimeList();
            List<AnalyticsTime> analyticsTimeList1Old = persistentEmployee.getAnalyticsTimeList1();
            List<AnalyticsTime> analyticsTimeList1New = employee.getAnalyticsTimeList1();
            List<Product> productListOld = persistentEmployee.getProductList();
            List<Product> productListNew = employee.getProductList();
            List<Product> productList1Old = persistentEmployee.getProductList1();
            List<Product> productList1New = employee.getProductList1();
            List<Provider> providerListOld = persistentEmployee.getProviderList();
            List<Provider> providerListNew = employee.getProviderList();
            List<Provider> providerList1Old = persistentEmployee.getProviderList1();
            List<Provider> providerList1New = employee.getProviderList1();
            List<Store> storeListOld = persistentEmployee.getStoreList();
            List<Store> storeListNew = employee.getStoreList();
            List<Store> storeList1Old = persistentEmployee.getStoreList1();
            List<Store> storeList1New = employee.getStoreList1();
            List<Store> storeList2Old = persistentEmployee.getStoreList2();
            List<Store> storeList2New = employee.getStoreList2();
            List<String> illegalOrphanMessages = null;
            for (Price priceListOldPrice : priceListOld) {
                if (!priceListNew.contains(priceListOldPrice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Price " + priceListOldPrice + " since its createdUser field is not nullable.");
                }
            }
            for (Price priceList1OldPrice : priceList1Old) {
                if (!priceList1New.contains(priceList1OldPrice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Price " + priceList1OldPrice + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeListOldEmployee + " since its createdUser field is not nullable.");
                }
            }
            for (Employee employeeList1OldEmployee : employeeList1Old) {
                if (!employeeList1New.contains(employeeList1OldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeList1OldEmployee + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (PriceType priceTypeListOldPriceType : priceTypeListOld) {
                if (!priceTypeListNew.contains(priceTypeListOldPriceType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PriceType " + priceTypeListOldPriceType + " since its createdUser field is not nullable.");
                }
            }
            for (PriceType priceTypeList1OldPriceType : priceTypeList1Old) {
                if (!priceTypeList1New.contains(priceTypeList1OldPriceType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PriceType " + priceTypeList1OldPriceType + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Record recordListOldRecord : recordListOld) {
                if (!recordListNew.contains(recordListOldRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Record " + recordListOldRecord + " since its createdUser field is not nullable.");
                }
            }
            for (Record recordList1OldRecord : recordList1Old) {
                if (!recordList1New.contains(recordList1OldRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Record " + recordList1OldRecord + " since its employee field is not nullable.");
                }
            }
            for (Record recordList2OldRecord : recordList2Old) {
                if (!recordList2New.contains(recordList2OldRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Record " + recordList2OldRecord + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Invoice invoiceListOldInvoice : invoiceListOld) {
                if (!invoiceListNew.contains(invoiceListOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceListOldInvoice + " since its createdUser field is not nullable.");
                }
            }
            for (Invoice invoiceList1OldInvoice : invoiceList1Old) {
                if (!invoiceList1New.contains(invoiceList1OldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceList1OldInvoice + " since its employee field is not nullable.");
                }
            }
            for (Invoice invoiceList2OldInvoice : invoiceList2Old) {
                if (!invoiceList2New.contains(invoiceList2OldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceList2OldInvoice + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Client clientListOldClient : clientListOld) {
                if (!clientListNew.contains(clientListOldClient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Client " + clientListOldClient + " since its createdUser field is not nullable.");
                }
            }
            for (Client clientList1OldClient : clientList1Old) {
                if (!clientList1New.contains(clientList1OldClient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Client " + clientList1OldClient + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Stock stockListOldStock : stockListOld) {
                if (!stockListNew.contains(stockListOldStock)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stock " + stockListOldStock + " since its createdUser field is not nullable.");
                }
            }
            for (Stock stockList1OldStock : stockList1Old) {
                if (!stockList1New.contains(stockList1OldStock)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stock " + stockList1OldStock + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Brand brandListOldBrand : brandListOld) {
                if (!brandListNew.contains(brandListOldBrand)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Brand " + brandListOldBrand + " since its createdUser field is not nullable.");
                }
            }
            for (Brand brandList1OldBrand : brandList1Old) {
                if (!brandList1New.contains(brandList1OldBrand)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Brand " + brandList1OldBrand + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (SexType sexTypeListOldSexType : sexTypeListOld) {
                if (!sexTypeListNew.contains(sexTypeListOldSexType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SexType " + sexTypeListOldSexType + " since its createdUser field is not nullable.");
                }
            }
            for (SexType sexTypeList1OldSexType : sexTypeList1Old) {
                if (!sexTypeList1New.contains(sexTypeList1OldSexType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SexType " + sexTypeList1OldSexType + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (PaymentType paymentTypeListOldPaymentType : paymentTypeListOld) {
                if (!paymentTypeListNew.contains(paymentTypeListOldPaymentType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PaymentType " + paymentTypeListOldPaymentType + " since its createdUser field is not nullable.");
                }
            }
            for (PaymentType paymentTypeList1OldPaymentType : paymentTypeList1Old) {
                if (!paymentTypeList1New.contains(paymentTypeList1OldPaymentType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PaymentType " + paymentTypeList1OldPaymentType + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (EmployeeType employeeTypeListOldEmployeeType : employeeTypeListOld) {
                if (!employeeTypeListNew.contains(employeeTypeListOldEmployeeType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeType " + employeeTypeListOldEmployeeType + " since its createdUser field is not nullable.");
                }
            }
            for (EmployeeType employeeTypeList1OldEmployeeType : employeeTypeList1Old) {
                if (!employeeTypeList1New.contains(employeeTypeList1OldEmployeeType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeType " + employeeTypeList1OldEmployeeType + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Item itemListOldItem : itemListOld) {
                if (!itemListNew.contains(itemListOldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemListOldItem + " since its createdUser field is not nullable.");
                }
            }
            for (Item itemList1OldItem : itemList1Old) {
                if (!itemList1New.contains(itemList1OldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemList1OldItem + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (ProductType productTypeListOldProductType : productTypeListOld) {
                if (!productTypeListNew.contains(productTypeListOldProductType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductType " + productTypeListOldProductType + " since its createdUser field is not nullable.");
                }
            }
            for (ProductType productTypeList1OldProductType : productTypeList1Old) {
                if (!productTypeList1New.contains(productTypeList1OldProductType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductType " + productTypeList1OldProductType + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (AnalyticsTime analyticsTimeListOldAnalyticsTime : analyticsTimeListOld) {
                if (!analyticsTimeListNew.contains(analyticsTimeListOldAnalyticsTime)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AnalyticsTime " + analyticsTimeListOldAnalyticsTime + " since its createdUser field is not nullable.");
                }
            }
            for (AnalyticsTime analyticsTimeList1OldAnalyticsTime : analyticsTimeList1Old) {
                if (!analyticsTimeList1New.contains(analyticsTimeList1OldAnalyticsTime)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AnalyticsTime " + analyticsTimeList1OldAnalyticsTime + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productListOldProduct + " since its createdUser field is not nullable.");
                }
            }
            for (Product productList1OldProduct : productList1Old) {
                if (!productList1New.contains(productList1OldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productList1OldProduct + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Provider providerListOldProvider : providerListOld) {
                if (!providerListNew.contains(providerListOldProvider)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Provider " + providerListOldProvider + " since its createdUser field is not nullable.");
                }
            }
            for (Provider providerList1OldProvider : providerList1Old) {
                if (!providerList1New.contains(providerList1OldProvider)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Provider " + providerList1OldProvider + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Store storeListOldStore : storeListOld) {
                if (!storeListNew.contains(storeListOldStore)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Store " + storeListOldStore + " since its createdUser field is not nullable.");
                }
            }
            for (Store storeList1OldStore : storeList1Old) {
                if (!storeList1New.contains(storeList1OldStore)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Store " + storeList1OldStore + " since its employee field is not nullable.");
                }
            }
            for (Store storeList2OldStore : storeList2Old) {
                if (!storeList2New.contains(storeList2OldStore)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Store " + storeList2OldStore + " since its lastModifiedUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                employee.setCreatedUser(createdUserNew);
            }
            if (employeeTypeNew != null) {
                employeeTypeNew = em.getReference(employeeTypeNew.getClass(), employeeTypeNew.getId());
                employee.setEmployeeType(employeeTypeNew);
            }
            if (sexTypeNew != null) {
                sexTypeNew = em.getReference(sexTypeNew.getClass(), sexTypeNew.getId());
                employee.setSexType(sexTypeNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                employee.setLastModifiedUser(lastModifiedUserNew);
            }
            if (languageTypeNew != null) {
                languageTypeNew = em.getReference(languageTypeNew.getClass(), languageTypeNew.getId());
                employee.setLanguageType(languageTypeNew);
            }
            List<Price> attachedPriceListNew = new ArrayList<Price>();
            for (Price priceListNewPriceToAttach : priceListNew) {
                priceListNewPriceToAttach = em.getReference(priceListNewPriceToAttach.getClass(), priceListNewPriceToAttach.getId());
                attachedPriceListNew.add(priceListNewPriceToAttach);
            }
            priceListNew = attachedPriceListNew;
            employee.setPriceList(priceListNew);
            List<Price> attachedPriceList1New = new ArrayList<Price>();
            for (Price priceList1NewPriceToAttach : priceList1New) {
                priceList1NewPriceToAttach = em.getReference(priceList1NewPriceToAttach.getClass(), priceList1NewPriceToAttach.getId());
                attachedPriceList1New.add(priceList1NewPriceToAttach);
            }
            priceList1New = attachedPriceList1New;
            employee.setPriceList1(priceList1New);
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getId());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            employee.setEmployeeList(employeeListNew);
            List<Employee> attachedEmployeeList1New = new ArrayList<Employee>();
            for (Employee employeeList1NewEmployeeToAttach : employeeList1New) {
                employeeList1NewEmployeeToAttach = em.getReference(employeeList1NewEmployeeToAttach.getClass(), employeeList1NewEmployeeToAttach.getId());
                attachedEmployeeList1New.add(employeeList1NewEmployeeToAttach);
            }
            employeeList1New = attachedEmployeeList1New;
            employee.setEmployeeList1(employeeList1New);
            List<PriceType> attachedPriceTypeListNew = new ArrayList<PriceType>();
            for (PriceType priceTypeListNewPriceTypeToAttach : priceTypeListNew) {
                priceTypeListNewPriceTypeToAttach = em.getReference(priceTypeListNewPriceTypeToAttach.getClass(), priceTypeListNewPriceTypeToAttach.getId());
                attachedPriceTypeListNew.add(priceTypeListNewPriceTypeToAttach);
            }
            priceTypeListNew = attachedPriceTypeListNew;
            employee.setPriceTypeList(priceTypeListNew);
            List<PriceType> attachedPriceTypeList1New = new ArrayList<PriceType>();
            for (PriceType priceTypeList1NewPriceTypeToAttach : priceTypeList1New) {
                priceTypeList1NewPriceTypeToAttach = em.getReference(priceTypeList1NewPriceTypeToAttach.getClass(), priceTypeList1NewPriceTypeToAttach.getId());
                attachedPriceTypeList1New.add(priceTypeList1NewPriceTypeToAttach);
            }
            priceTypeList1New = attachedPriceTypeList1New;
            employee.setPriceTypeList1(priceTypeList1New);
            List<Record> attachedRecordListNew = new ArrayList<Record>();
            for (Record recordListNewRecordToAttach : recordListNew) {
                recordListNewRecordToAttach = em.getReference(recordListNewRecordToAttach.getClass(), recordListNewRecordToAttach.getId());
                attachedRecordListNew.add(recordListNewRecordToAttach);
            }
            recordListNew = attachedRecordListNew;
            employee.setRecordList(recordListNew);
            List<Record> attachedRecordList1New = new ArrayList<Record>();
            for (Record recordList1NewRecordToAttach : recordList1New) {
                recordList1NewRecordToAttach = em.getReference(recordList1NewRecordToAttach.getClass(), recordList1NewRecordToAttach.getId());
                attachedRecordList1New.add(recordList1NewRecordToAttach);
            }
            recordList1New = attachedRecordList1New;
            employee.setRecordList1(recordList1New);
            List<Record> attachedRecordList2New = new ArrayList<Record>();
            for (Record recordList2NewRecordToAttach : recordList2New) {
                recordList2NewRecordToAttach = em.getReference(recordList2NewRecordToAttach.getClass(), recordList2NewRecordToAttach.getId());
                attachedRecordList2New.add(recordList2NewRecordToAttach);
            }
            recordList2New = attachedRecordList2New;
            employee.setRecordList2(recordList2New);
            List<Invoice> attachedInvoiceListNew = new ArrayList<Invoice>();
            for (Invoice invoiceListNewInvoiceToAttach : invoiceListNew) {
                invoiceListNewInvoiceToAttach = em.getReference(invoiceListNewInvoiceToAttach.getClass(), invoiceListNewInvoiceToAttach.getId());
                attachedInvoiceListNew.add(invoiceListNewInvoiceToAttach);
            }
            invoiceListNew = attachedInvoiceListNew;
            employee.setInvoiceList(invoiceListNew);
            List<Invoice> attachedInvoiceList1New = new ArrayList<Invoice>();
            for (Invoice invoiceList1NewInvoiceToAttach : invoiceList1New) {
                invoiceList1NewInvoiceToAttach = em.getReference(invoiceList1NewInvoiceToAttach.getClass(), invoiceList1NewInvoiceToAttach.getId());
                attachedInvoiceList1New.add(invoiceList1NewInvoiceToAttach);
            }
            invoiceList1New = attachedInvoiceList1New;
            employee.setInvoiceList1(invoiceList1New);
            List<Invoice> attachedInvoiceList2New = new ArrayList<Invoice>();
            for (Invoice invoiceList2NewInvoiceToAttach : invoiceList2New) {
                invoiceList2NewInvoiceToAttach = em.getReference(invoiceList2NewInvoiceToAttach.getClass(), invoiceList2NewInvoiceToAttach.getId());
                attachedInvoiceList2New.add(invoiceList2NewInvoiceToAttach);
            }
            invoiceList2New = attachedInvoiceList2New;
            employee.setInvoiceList2(invoiceList2New);
            List<Client> attachedClientListNew = new ArrayList<Client>();
            for (Client clientListNewClientToAttach : clientListNew) {
                clientListNewClientToAttach = em.getReference(clientListNewClientToAttach.getClass(), clientListNewClientToAttach.getId());
                attachedClientListNew.add(clientListNewClientToAttach);
            }
            clientListNew = attachedClientListNew;
            employee.setClientList(clientListNew);
            List<Client> attachedClientList1New = new ArrayList<Client>();
            for (Client clientList1NewClientToAttach : clientList1New) {
                clientList1NewClientToAttach = em.getReference(clientList1NewClientToAttach.getClass(), clientList1NewClientToAttach.getId());
                attachedClientList1New.add(clientList1NewClientToAttach);
            }
            clientList1New = attachedClientList1New;
            employee.setClientList1(clientList1New);
            List<Stock> attachedStockListNew = new ArrayList<Stock>();
            for (Stock stockListNewStockToAttach : stockListNew) {
                stockListNewStockToAttach = em.getReference(stockListNewStockToAttach.getClass(), stockListNewStockToAttach.getId());
                attachedStockListNew.add(stockListNewStockToAttach);
            }
            stockListNew = attachedStockListNew;
            employee.setStockList(stockListNew);
            List<Stock> attachedStockList1New = new ArrayList<Stock>();
            for (Stock stockList1NewStockToAttach : stockList1New) {
                stockList1NewStockToAttach = em.getReference(stockList1NewStockToAttach.getClass(), stockList1NewStockToAttach.getId());
                attachedStockList1New.add(stockList1NewStockToAttach);
            }
            stockList1New = attachedStockList1New;
            employee.setStockList1(stockList1New);
            List<Brand> attachedBrandListNew = new ArrayList<Brand>();
            for (Brand brandListNewBrandToAttach : brandListNew) {
                brandListNewBrandToAttach = em.getReference(brandListNewBrandToAttach.getClass(), brandListNewBrandToAttach.getId());
                attachedBrandListNew.add(brandListNewBrandToAttach);
            }
            brandListNew = attachedBrandListNew;
            employee.setBrandList(brandListNew);
            List<Brand> attachedBrandList1New = new ArrayList<Brand>();
            for (Brand brandList1NewBrandToAttach : brandList1New) {
                brandList1NewBrandToAttach = em.getReference(brandList1NewBrandToAttach.getClass(), brandList1NewBrandToAttach.getId());
                attachedBrandList1New.add(brandList1NewBrandToAttach);
            }
            brandList1New = attachedBrandList1New;
            employee.setBrandList1(brandList1New);
            List<SexType> attachedSexTypeListNew = new ArrayList<SexType>();
            for (SexType sexTypeListNewSexTypeToAttach : sexTypeListNew) {
                sexTypeListNewSexTypeToAttach = em.getReference(sexTypeListNewSexTypeToAttach.getClass(), sexTypeListNewSexTypeToAttach.getId());
                attachedSexTypeListNew.add(sexTypeListNewSexTypeToAttach);
            }
            sexTypeListNew = attachedSexTypeListNew;
            employee.setSexTypeList(sexTypeListNew);
            List<SexType> attachedSexTypeList1New = new ArrayList<SexType>();
            for (SexType sexTypeList1NewSexTypeToAttach : sexTypeList1New) {
                sexTypeList1NewSexTypeToAttach = em.getReference(sexTypeList1NewSexTypeToAttach.getClass(), sexTypeList1NewSexTypeToAttach.getId());
                attachedSexTypeList1New.add(sexTypeList1NewSexTypeToAttach);
            }
            sexTypeList1New = attachedSexTypeList1New;
            employee.setSexTypeList1(sexTypeList1New);
            List<PaymentType> attachedPaymentTypeListNew = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeListNewPaymentTypeToAttach : paymentTypeListNew) {
                paymentTypeListNewPaymentTypeToAttach = em.getReference(paymentTypeListNewPaymentTypeToAttach.getClass(), paymentTypeListNewPaymentTypeToAttach.getId());
                attachedPaymentTypeListNew.add(paymentTypeListNewPaymentTypeToAttach);
            }
            paymentTypeListNew = attachedPaymentTypeListNew;
            employee.setPaymentTypeList(paymentTypeListNew);
            List<PaymentType> attachedPaymentTypeList1New = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeList1NewPaymentTypeToAttach : paymentTypeList1New) {
                paymentTypeList1NewPaymentTypeToAttach = em.getReference(paymentTypeList1NewPaymentTypeToAttach.getClass(), paymentTypeList1NewPaymentTypeToAttach.getId());
                attachedPaymentTypeList1New.add(paymentTypeList1NewPaymentTypeToAttach);
            }
            paymentTypeList1New = attachedPaymentTypeList1New;
            employee.setPaymentTypeList1(paymentTypeList1New);
            List<EmployeeType> attachedEmployeeTypeListNew = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListNewEmployeeTypeToAttach : employeeTypeListNew) {
                employeeTypeListNewEmployeeTypeToAttach = em.getReference(employeeTypeListNewEmployeeTypeToAttach.getClass(), employeeTypeListNewEmployeeTypeToAttach.getId());
                attachedEmployeeTypeListNew.add(employeeTypeListNewEmployeeTypeToAttach);
            }
            employeeTypeListNew = attachedEmployeeTypeListNew;
            employee.setEmployeeTypeList(employeeTypeListNew);
            List<EmployeeType> attachedEmployeeTypeList1New = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeList1NewEmployeeTypeToAttach : employeeTypeList1New) {
                employeeTypeList1NewEmployeeTypeToAttach = em.getReference(employeeTypeList1NewEmployeeTypeToAttach.getClass(), employeeTypeList1NewEmployeeTypeToAttach.getId());
                attachedEmployeeTypeList1New.add(employeeTypeList1NewEmployeeTypeToAttach);
            }
            employeeTypeList1New = attachedEmployeeTypeList1New;
            employee.setEmployeeTypeList1(employeeTypeList1New);
            List<Item> attachedItemListNew = new ArrayList<Item>();
            for (Item itemListNewItemToAttach : itemListNew) {
                itemListNewItemToAttach = em.getReference(itemListNewItemToAttach.getClass(), itemListNewItemToAttach.getId());
                attachedItemListNew.add(itemListNewItemToAttach);
            }
            itemListNew = attachedItemListNew;
            employee.setItemList(itemListNew);
            List<Item> attachedItemList1New = new ArrayList<Item>();
            for (Item itemList1NewItemToAttach : itemList1New) {
                itemList1NewItemToAttach = em.getReference(itemList1NewItemToAttach.getClass(), itemList1NewItemToAttach.getId());
                attachedItemList1New.add(itemList1NewItemToAttach);
            }
            itemList1New = attachedItemList1New;
            employee.setItemList1(itemList1New);
            List<ProductType> attachedProductTypeListNew = new ArrayList<ProductType>();
            for (ProductType productTypeListNewProductTypeToAttach : productTypeListNew) {
                productTypeListNewProductTypeToAttach = em.getReference(productTypeListNewProductTypeToAttach.getClass(), productTypeListNewProductTypeToAttach.getId());
                attachedProductTypeListNew.add(productTypeListNewProductTypeToAttach);
            }
            productTypeListNew = attachedProductTypeListNew;
            employee.setProductTypeList(productTypeListNew);
            List<ProductType> attachedProductTypeList1New = new ArrayList<ProductType>();
            for (ProductType productTypeList1NewProductTypeToAttach : productTypeList1New) {
                productTypeList1NewProductTypeToAttach = em.getReference(productTypeList1NewProductTypeToAttach.getClass(), productTypeList1NewProductTypeToAttach.getId());
                attachedProductTypeList1New.add(productTypeList1NewProductTypeToAttach);
            }
            productTypeList1New = attachedProductTypeList1New;
            employee.setProductTypeList1(productTypeList1New);
            List<AnalyticsTime> attachedAnalyticsTimeListNew = new ArrayList<AnalyticsTime>();
            for (AnalyticsTime analyticsTimeListNewAnalyticsTimeToAttach : analyticsTimeListNew) {
                analyticsTimeListNewAnalyticsTimeToAttach = em.getReference(analyticsTimeListNewAnalyticsTimeToAttach.getClass(), analyticsTimeListNewAnalyticsTimeToAttach.getId());
                attachedAnalyticsTimeListNew.add(analyticsTimeListNewAnalyticsTimeToAttach);
            }
            analyticsTimeListNew = attachedAnalyticsTimeListNew;
            employee.setAnalyticsTimeList(analyticsTimeListNew);
            List<AnalyticsTime> attachedAnalyticsTimeList1New = new ArrayList<AnalyticsTime>();
            for (AnalyticsTime analyticsTimeList1NewAnalyticsTimeToAttach : analyticsTimeList1New) {
                analyticsTimeList1NewAnalyticsTimeToAttach = em.getReference(analyticsTimeList1NewAnalyticsTimeToAttach.getClass(), analyticsTimeList1NewAnalyticsTimeToAttach.getId());
                attachedAnalyticsTimeList1New.add(analyticsTimeList1NewAnalyticsTimeToAttach);
            }
            analyticsTimeList1New = attachedAnalyticsTimeList1New;
            employee.setAnalyticsTimeList1(analyticsTimeList1New);
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getId());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            employee.setProductList(productListNew);
            List<Product> attachedProductList1New = new ArrayList<Product>();
            for (Product productList1NewProductToAttach : productList1New) {
                productList1NewProductToAttach = em.getReference(productList1NewProductToAttach.getClass(), productList1NewProductToAttach.getId());
                attachedProductList1New.add(productList1NewProductToAttach);
            }
            productList1New = attachedProductList1New;
            employee.setProductList1(productList1New);
            List<Provider> attachedProviderListNew = new ArrayList<Provider>();
            for (Provider providerListNewProviderToAttach : providerListNew) {
                providerListNewProviderToAttach = em.getReference(providerListNewProviderToAttach.getClass(), providerListNewProviderToAttach.getId());
                attachedProviderListNew.add(providerListNewProviderToAttach);
            }
            providerListNew = attachedProviderListNew;
            employee.setProviderList(providerListNew);
            List<Provider> attachedProviderList1New = new ArrayList<Provider>();
            for (Provider providerList1NewProviderToAttach : providerList1New) {
                providerList1NewProviderToAttach = em.getReference(providerList1NewProviderToAttach.getClass(), providerList1NewProviderToAttach.getId());
                attachedProviderList1New.add(providerList1NewProviderToAttach);
            }
            providerList1New = attachedProviderList1New;
            employee.setProviderList1(providerList1New);
            List<Store> attachedStoreListNew = new ArrayList<Store>();
            for (Store storeListNewStoreToAttach : storeListNew) {
                storeListNewStoreToAttach = em.getReference(storeListNewStoreToAttach.getClass(), storeListNewStoreToAttach.getId());
                attachedStoreListNew.add(storeListNewStoreToAttach);
            }
            storeListNew = attachedStoreListNew;
            employee.setStoreList(storeListNew);
            List<Store> attachedStoreList1New = new ArrayList<Store>();
            for (Store storeList1NewStoreToAttach : storeList1New) {
                storeList1NewStoreToAttach = em.getReference(storeList1NewStoreToAttach.getClass(), storeList1NewStoreToAttach.getId());
                attachedStoreList1New.add(storeList1NewStoreToAttach);
            }
            storeList1New = attachedStoreList1New;
            employee.setStoreList1(storeList1New);
            List<Store> attachedStoreList2New = new ArrayList<Store>();
            for (Store storeList2NewStoreToAttach : storeList2New) {
                storeList2NewStoreToAttach = em.getReference(storeList2NewStoreToAttach.getClass(), storeList2NewStoreToAttach.getId());
                attachedStoreList2New.add(storeList2NewStoreToAttach);
            }
            storeList2New = attachedStoreList2New;
            employee.setStoreList2(storeList2New);
            employee = em.merge(employee);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getEmployeeList().remove(employee);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getEmployeeList().add(employee);
                createdUserNew = em.merge(createdUserNew);
            }
            if (employeeTypeOld != null && !employeeTypeOld.equals(employeeTypeNew)) {
                employeeTypeOld.getEmployeeList().remove(employee);
                employeeTypeOld = em.merge(employeeTypeOld);
            }
            if (employeeTypeNew != null && !employeeTypeNew.equals(employeeTypeOld)) {
                employeeTypeNew.getEmployeeList().add(employee);
                employeeTypeNew = em.merge(employeeTypeNew);
            }
            if (sexTypeOld != null && !sexTypeOld.equals(sexTypeNew)) {
                sexTypeOld.getEmployeeList().remove(employee);
                sexTypeOld = em.merge(sexTypeOld);
            }
            if (sexTypeNew != null && !sexTypeNew.equals(sexTypeOld)) {
                sexTypeNew.getEmployeeList().add(employee);
                sexTypeNew = em.merge(sexTypeNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getEmployeeList().remove(employee);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getEmployeeList().add(employee);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            if (languageTypeOld != null && !languageTypeOld.equals(languageTypeNew)) {
                languageTypeOld.getEmployeeList().remove(employee);
                languageTypeOld = em.merge(languageTypeOld);
            }
            if (languageTypeNew != null && !languageTypeNew.equals(languageTypeOld)) {
                languageTypeNew.getEmployeeList().add(employee);
                languageTypeNew = em.merge(languageTypeNew);
            }
            for (Price priceListNewPrice : priceListNew) {
                if (!priceListOld.contains(priceListNewPrice)) {
                    Employee oldCreatedUserOfPriceListNewPrice = priceListNewPrice.getCreatedUser();
                    priceListNewPrice.setCreatedUser(employee);
                    priceListNewPrice = em.merge(priceListNewPrice);
                    if (oldCreatedUserOfPriceListNewPrice != null && !oldCreatedUserOfPriceListNewPrice.equals(employee)) {
                        oldCreatedUserOfPriceListNewPrice.getPriceList().remove(priceListNewPrice);
                        oldCreatedUserOfPriceListNewPrice = em.merge(oldCreatedUserOfPriceListNewPrice);
                    }
                }
            }
            for (Price priceList1NewPrice : priceList1New) {
                if (!priceList1Old.contains(priceList1NewPrice)) {
                    Employee oldLastModifiedUserOfPriceList1NewPrice = priceList1NewPrice.getLastModifiedUser();
                    priceList1NewPrice.setLastModifiedUser(employee);
                    priceList1NewPrice = em.merge(priceList1NewPrice);
                    if (oldLastModifiedUserOfPriceList1NewPrice != null && !oldLastModifiedUserOfPriceList1NewPrice.equals(employee)) {
                        oldLastModifiedUserOfPriceList1NewPrice.getPriceList1().remove(priceList1NewPrice);
                        oldLastModifiedUserOfPriceList1NewPrice = em.merge(oldLastModifiedUserOfPriceList1NewPrice);
                    }
                }
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    Employee oldCreatedUserOfEmployeeListNewEmployee = employeeListNewEmployee.getCreatedUser();
                    employeeListNewEmployee.setCreatedUser(employee);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldCreatedUserOfEmployeeListNewEmployee != null && !oldCreatedUserOfEmployeeListNewEmployee.equals(employee)) {
                        oldCreatedUserOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldCreatedUserOfEmployeeListNewEmployee = em.merge(oldCreatedUserOfEmployeeListNewEmployee);
                    }
                }
            }
            for (Employee employeeList1NewEmployee : employeeList1New) {
                if (!employeeList1Old.contains(employeeList1NewEmployee)) {
                    Employee oldLastModifiedUserOfEmployeeList1NewEmployee = employeeList1NewEmployee.getLastModifiedUser();
                    employeeList1NewEmployee.setLastModifiedUser(employee);
                    employeeList1NewEmployee = em.merge(employeeList1NewEmployee);
                    if (oldLastModifiedUserOfEmployeeList1NewEmployee != null && !oldLastModifiedUserOfEmployeeList1NewEmployee.equals(employee)) {
                        oldLastModifiedUserOfEmployeeList1NewEmployee.getEmployeeList1().remove(employeeList1NewEmployee);
                        oldLastModifiedUserOfEmployeeList1NewEmployee = em.merge(oldLastModifiedUserOfEmployeeList1NewEmployee);
                    }
                }
            }
            for (PriceType priceTypeListNewPriceType : priceTypeListNew) {
                if (!priceTypeListOld.contains(priceTypeListNewPriceType)) {
                    Employee oldCreatedUserOfPriceTypeListNewPriceType = priceTypeListNewPriceType.getCreatedUser();
                    priceTypeListNewPriceType.setCreatedUser(employee);
                    priceTypeListNewPriceType = em.merge(priceTypeListNewPriceType);
                    if (oldCreatedUserOfPriceTypeListNewPriceType != null && !oldCreatedUserOfPriceTypeListNewPriceType.equals(employee)) {
                        oldCreatedUserOfPriceTypeListNewPriceType.getPriceTypeList().remove(priceTypeListNewPriceType);
                        oldCreatedUserOfPriceTypeListNewPriceType = em.merge(oldCreatedUserOfPriceTypeListNewPriceType);
                    }
                }
            }
            for (PriceType priceTypeList1NewPriceType : priceTypeList1New) {
                if (!priceTypeList1Old.contains(priceTypeList1NewPriceType)) {
                    Employee oldLastModifiedUserOfPriceTypeList1NewPriceType = priceTypeList1NewPriceType.getLastModifiedUser();
                    priceTypeList1NewPriceType.setLastModifiedUser(employee);
                    priceTypeList1NewPriceType = em.merge(priceTypeList1NewPriceType);
                    if (oldLastModifiedUserOfPriceTypeList1NewPriceType != null && !oldLastModifiedUserOfPriceTypeList1NewPriceType.equals(employee)) {
                        oldLastModifiedUserOfPriceTypeList1NewPriceType.getPriceTypeList1().remove(priceTypeList1NewPriceType);
                        oldLastModifiedUserOfPriceTypeList1NewPriceType = em.merge(oldLastModifiedUserOfPriceTypeList1NewPriceType);
                    }
                }
            }
            for (Record recordListNewRecord : recordListNew) {
                if (!recordListOld.contains(recordListNewRecord)) {
                    Employee oldCreatedUserOfRecordListNewRecord = recordListNewRecord.getCreatedUser();
                    recordListNewRecord.setCreatedUser(employee);
                    recordListNewRecord = em.merge(recordListNewRecord);
                    if (oldCreatedUserOfRecordListNewRecord != null && !oldCreatedUserOfRecordListNewRecord.equals(employee)) {
                        oldCreatedUserOfRecordListNewRecord.getRecordList().remove(recordListNewRecord);
                        oldCreatedUserOfRecordListNewRecord = em.merge(oldCreatedUserOfRecordListNewRecord);
                    }
                }
            }
            for (Record recordList1NewRecord : recordList1New) {
                if (!recordList1Old.contains(recordList1NewRecord)) {
                    Employee oldEmployeeOfRecordList1NewRecord = recordList1NewRecord.getEmployee();
                    recordList1NewRecord.setEmployee(employee);
                    recordList1NewRecord = em.merge(recordList1NewRecord);
                    if (oldEmployeeOfRecordList1NewRecord != null && !oldEmployeeOfRecordList1NewRecord.equals(employee)) {
                        oldEmployeeOfRecordList1NewRecord.getRecordList1().remove(recordList1NewRecord);
                        oldEmployeeOfRecordList1NewRecord = em.merge(oldEmployeeOfRecordList1NewRecord);
                    }
                }
            }
            for (Record recordList2NewRecord : recordList2New) {
                if (!recordList2Old.contains(recordList2NewRecord)) {
                    Employee oldLastModifiedUserOfRecordList2NewRecord = recordList2NewRecord.getLastModifiedUser();
                    recordList2NewRecord.setLastModifiedUser(employee);
                    recordList2NewRecord = em.merge(recordList2NewRecord);
                    if (oldLastModifiedUserOfRecordList2NewRecord != null && !oldLastModifiedUserOfRecordList2NewRecord.equals(employee)) {
                        oldLastModifiedUserOfRecordList2NewRecord.getRecordList2().remove(recordList2NewRecord);
                        oldLastModifiedUserOfRecordList2NewRecord = em.merge(oldLastModifiedUserOfRecordList2NewRecord);
                    }
                }
            }
            for (Invoice invoiceListNewInvoice : invoiceListNew) {
                if (!invoiceListOld.contains(invoiceListNewInvoice)) {
                    Employee oldCreatedUserOfInvoiceListNewInvoice = invoiceListNewInvoice.getCreatedUser();
                    invoiceListNewInvoice.setCreatedUser(employee);
                    invoiceListNewInvoice = em.merge(invoiceListNewInvoice);
                    if (oldCreatedUserOfInvoiceListNewInvoice != null && !oldCreatedUserOfInvoiceListNewInvoice.equals(employee)) {
                        oldCreatedUserOfInvoiceListNewInvoice.getInvoiceList().remove(invoiceListNewInvoice);
                        oldCreatedUserOfInvoiceListNewInvoice = em.merge(oldCreatedUserOfInvoiceListNewInvoice);
                    }
                }
            }
            for (Invoice invoiceList1NewInvoice : invoiceList1New) {
                if (!invoiceList1Old.contains(invoiceList1NewInvoice)) {
                    Employee oldEmployeeOfInvoiceList1NewInvoice = invoiceList1NewInvoice.getEmployee();
                    invoiceList1NewInvoice.setEmployee(employee);
                    invoiceList1NewInvoice = em.merge(invoiceList1NewInvoice);
                    if (oldEmployeeOfInvoiceList1NewInvoice != null && !oldEmployeeOfInvoiceList1NewInvoice.equals(employee)) {
                        oldEmployeeOfInvoiceList1NewInvoice.getInvoiceList1().remove(invoiceList1NewInvoice);
                        oldEmployeeOfInvoiceList1NewInvoice = em.merge(oldEmployeeOfInvoiceList1NewInvoice);
                    }
                }
            }
            for (Invoice invoiceList2NewInvoice : invoiceList2New) {
                if (!invoiceList2Old.contains(invoiceList2NewInvoice)) {
                    Employee oldLastModifiedUserOfInvoiceList2NewInvoice = invoiceList2NewInvoice.getLastModifiedUser();
                    invoiceList2NewInvoice.setLastModifiedUser(employee);
                    invoiceList2NewInvoice = em.merge(invoiceList2NewInvoice);
                    if (oldLastModifiedUserOfInvoiceList2NewInvoice != null && !oldLastModifiedUserOfInvoiceList2NewInvoice.equals(employee)) {
                        oldLastModifiedUserOfInvoiceList2NewInvoice.getInvoiceList2().remove(invoiceList2NewInvoice);
                        oldLastModifiedUserOfInvoiceList2NewInvoice = em.merge(oldLastModifiedUserOfInvoiceList2NewInvoice);
                    }
                }
            }
            for (Client clientListNewClient : clientListNew) {
                if (!clientListOld.contains(clientListNewClient)) {
                    Employee oldCreatedUserOfClientListNewClient = clientListNewClient.getCreatedUser();
                    clientListNewClient.setCreatedUser(employee);
                    clientListNewClient = em.merge(clientListNewClient);
                    if (oldCreatedUserOfClientListNewClient != null && !oldCreatedUserOfClientListNewClient.equals(employee)) {
                        oldCreatedUserOfClientListNewClient.getClientList().remove(clientListNewClient);
                        oldCreatedUserOfClientListNewClient = em.merge(oldCreatedUserOfClientListNewClient);
                    }
                }
            }
            for (Client clientList1NewClient : clientList1New) {
                if (!clientList1Old.contains(clientList1NewClient)) {
                    Employee oldLastModifiedUserOfClientList1NewClient = clientList1NewClient.getLastModifiedUser();
                    clientList1NewClient.setLastModifiedUser(employee);
                    clientList1NewClient = em.merge(clientList1NewClient);
                    if (oldLastModifiedUserOfClientList1NewClient != null && !oldLastModifiedUserOfClientList1NewClient.equals(employee)) {
                        oldLastModifiedUserOfClientList1NewClient.getClientList1().remove(clientList1NewClient);
                        oldLastModifiedUserOfClientList1NewClient = em.merge(oldLastModifiedUserOfClientList1NewClient);
                    }
                }
            }
            for (Stock stockListNewStock : stockListNew) {
                if (!stockListOld.contains(stockListNewStock)) {
                    Employee oldCreatedUserOfStockListNewStock = stockListNewStock.getCreatedUser();
                    stockListNewStock.setCreatedUser(employee);
                    stockListNewStock = em.merge(stockListNewStock);
                    if (oldCreatedUserOfStockListNewStock != null && !oldCreatedUserOfStockListNewStock.equals(employee)) {
                        oldCreatedUserOfStockListNewStock.getStockList().remove(stockListNewStock);
                        oldCreatedUserOfStockListNewStock = em.merge(oldCreatedUserOfStockListNewStock);
                    }
                }
            }
            for (Stock stockList1NewStock : stockList1New) {
                if (!stockList1Old.contains(stockList1NewStock)) {
                    Employee oldLastModifiedUserOfStockList1NewStock = stockList1NewStock.getLastModifiedUser();
                    stockList1NewStock.setLastModifiedUser(employee);
                    stockList1NewStock = em.merge(stockList1NewStock);
                    if (oldLastModifiedUserOfStockList1NewStock != null && !oldLastModifiedUserOfStockList1NewStock.equals(employee)) {
                        oldLastModifiedUserOfStockList1NewStock.getStockList1().remove(stockList1NewStock);
                        oldLastModifiedUserOfStockList1NewStock = em.merge(oldLastModifiedUserOfStockList1NewStock);
                    }
                }
            }
            for (Brand brandListNewBrand : brandListNew) {
                if (!brandListOld.contains(brandListNewBrand)) {
                    Employee oldCreatedUserOfBrandListNewBrand = brandListNewBrand.getCreatedUser();
                    brandListNewBrand.setCreatedUser(employee);
                    brandListNewBrand = em.merge(brandListNewBrand);
                    if (oldCreatedUserOfBrandListNewBrand != null && !oldCreatedUserOfBrandListNewBrand.equals(employee)) {
                        oldCreatedUserOfBrandListNewBrand.getBrandList().remove(brandListNewBrand);
                        oldCreatedUserOfBrandListNewBrand = em.merge(oldCreatedUserOfBrandListNewBrand);
                    }
                }
            }
            for (Brand brandList1NewBrand : brandList1New) {
                if (!brandList1Old.contains(brandList1NewBrand)) {
                    Employee oldLastModifiedUserOfBrandList1NewBrand = brandList1NewBrand.getLastModifiedUser();
                    brandList1NewBrand.setLastModifiedUser(employee);
                    brandList1NewBrand = em.merge(brandList1NewBrand);
                    if (oldLastModifiedUserOfBrandList1NewBrand != null && !oldLastModifiedUserOfBrandList1NewBrand.equals(employee)) {
                        oldLastModifiedUserOfBrandList1NewBrand.getBrandList1().remove(brandList1NewBrand);
                        oldLastModifiedUserOfBrandList1NewBrand = em.merge(oldLastModifiedUserOfBrandList1NewBrand);
                    }
                }
            }
            for (SexType sexTypeListNewSexType : sexTypeListNew) {
                if (!sexTypeListOld.contains(sexTypeListNewSexType)) {
                    Employee oldCreatedUserOfSexTypeListNewSexType = sexTypeListNewSexType.getCreatedUser();
                    sexTypeListNewSexType.setCreatedUser(employee);
                    sexTypeListNewSexType = em.merge(sexTypeListNewSexType);
                    if (oldCreatedUserOfSexTypeListNewSexType != null && !oldCreatedUserOfSexTypeListNewSexType.equals(employee)) {
                        oldCreatedUserOfSexTypeListNewSexType.getSexTypeList().remove(sexTypeListNewSexType);
                        oldCreatedUserOfSexTypeListNewSexType = em.merge(oldCreatedUserOfSexTypeListNewSexType);
                    }
                }
            }
            for (SexType sexTypeList1NewSexType : sexTypeList1New) {
                if (!sexTypeList1Old.contains(sexTypeList1NewSexType)) {
                    Employee oldLastModifiedUserOfSexTypeList1NewSexType = sexTypeList1NewSexType.getLastModifiedUser();
                    sexTypeList1NewSexType.setLastModifiedUser(employee);
                    sexTypeList1NewSexType = em.merge(sexTypeList1NewSexType);
                    if (oldLastModifiedUserOfSexTypeList1NewSexType != null && !oldLastModifiedUserOfSexTypeList1NewSexType.equals(employee)) {
                        oldLastModifiedUserOfSexTypeList1NewSexType.getSexTypeList1().remove(sexTypeList1NewSexType);
                        oldLastModifiedUserOfSexTypeList1NewSexType = em.merge(oldLastModifiedUserOfSexTypeList1NewSexType);
                    }
                }
            }
            for (PaymentType paymentTypeListNewPaymentType : paymentTypeListNew) {
                if (!paymentTypeListOld.contains(paymentTypeListNewPaymentType)) {
                    Employee oldCreatedUserOfPaymentTypeListNewPaymentType = paymentTypeListNewPaymentType.getCreatedUser();
                    paymentTypeListNewPaymentType.setCreatedUser(employee);
                    paymentTypeListNewPaymentType = em.merge(paymentTypeListNewPaymentType);
                    if (oldCreatedUserOfPaymentTypeListNewPaymentType != null && !oldCreatedUserOfPaymentTypeListNewPaymentType.equals(employee)) {
                        oldCreatedUserOfPaymentTypeListNewPaymentType.getPaymentTypeList().remove(paymentTypeListNewPaymentType);
                        oldCreatedUserOfPaymentTypeListNewPaymentType = em.merge(oldCreatedUserOfPaymentTypeListNewPaymentType);
                    }
                }
            }
            for (PaymentType paymentTypeList1NewPaymentType : paymentTypeList1New) {
                if (!paymentTypeList1Old.contains(paymentTypeList1NewPaymentType)) {
                    Employee oldLastModifiedUserOfPaymentTypeList1NewPaymentType = paymentTypeList1NewPaymentType.getLastModifiedUser();
                    paymentTypeList1NewPaymentType.setLastModifiedUser(employee);
                    paymentTypeList1NewPaymentType = em.merge(paymentTypeList1NewPaymentType);
                    if (oldLastModifiedUserOfPaymentTypeList1NewPaymentType != null && !oldLastModifiedUserOfPaymentTypeList1NewPaymentType.equals(employee)) {
                        oldLastModifiedUserOfPaymentTypeList1NewPaymentType.getPaymentTypeList1().remove(paymentTypeList1NewPaymentType);
                        oldLastModifiedUserOfPaymentTypeList1NewPaymentType = em.merge(oldLastModifiedUserOfPaymentTypeList1NewPaymentType);
                    }
                }
            }
            for (EmployeeType employeeTypeListNewEmployeeType : employeeTypeListNew) {
                if (!employeeTypeListOld.contains(employeeTypeListNewEmployeeType)) {
                    Employee oldCreatedUserOfEmployeeTypeListNewEmployeeType = employeeTypeListNewEmployeeType.getCreatedUser();
                    employeeTypeListNewEmployeeType.setCreatedUser(employee);
                    employeeTypeListNewEmployeeType = em.merge(employeeTypeListNewEmployeeType);
                    if (oldCreatedUserOfEmployeeTypeListNewEmployeeType != null && !oldCreatedUserOfEmployeeTypeListNewEmployeeType.equals(employee)) {
                        oldCreatedUserOfEmployeeTypeListNewEmployeeType.getEmployeeTypeList().remove(employeeTypeListNewEmployeeType);
                        oldCreatedUserOfEmployeeTypeListNewEmployeeType = em.merge(oldCreatedUserOfEmployeeTypeListNewEmployeeType);
                    }
                }
            }
            for (EmployeeType employeeTypeList1NewEmployeeType : employeeTypeList1New) {
                if (!employeeTypeList1Old.contains(employeeTypeList1NewEmployeeType)) {
                    Employee oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType = employeeTypeList1NewEmployeeType.getLastModifiedUser();
                    employeeTypeList1NewEmployeeType.setLastModifiedUser(employee);
                    employeeTypeList1NewEmployeeType = em.merge(employeeTypeList1NewEmployeeType);
                    if (oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType != null && !oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType.equals(employee)) {
                        oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType.getEmployeeTypeList1().remove(employeeTypeList1NewEmployeeType);
                        oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType = em.merge(oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType);
                    }
                }
            }
            for (Item itemListNewItem : itemListNew) {
                if (!itemListOld.contains(itemListNewItem)) {
                    Employee oldCreatedUserOfItemListNewItem = itemListNewItem.getCreatedUser();
                    itemListNewItem.setCreatedUser(employee);
                    itemListNewItem = em.merge(itemListNewItem);
                    if (oldCreatedUserOfItemListNewItem != null && !oldCreatedUserOfItemListNewItem.equals(employee)) {
                        oldCreatedUserOfItemListNewItem.getItemList().remove(itemListNewItem);
                        oldCreatedUserOfItemListNewItem = em.merge(oldCreatedUserOfItemListNewItem);
                    }
                }
            }
            for (Item itemList1NewItem : itemList1New) {
                if (!itemList1Old.contains(itemList1NewItem)) {
                    Employee oldLastModifiedUserOfItemList1NewItem = itemList1NewItem.getLastModifiedUser();
                    itemList1NewItem.setLastModifiedUser(employee);
                    itemList1NewItem = em.merge(itemList1NewItem);
                    if (oldLastModifiedUserOfItemList1NewItem != null && !oldLastModifiedUserOfItemList1NewItem.equals(employee)) {
                        oldLastModifiedUserOfItemList1NewItem.getItemList1().remove(itemList1NewItem);
                        oldLastModifiedUserOfItemList1NewItem = em.merge(oldLastModifiedUserOfItemList1NewItem);
                    }
                }
            }
            for (ProductType productTypeListNewProductType : productTypeListNew) {
                if (!productTypeListOld.contains(productTypeListNewProductType)) {
                    Employee oldCreatedUserOfProductTypeListNewProductType = productTypeListNewProductType.getCreatedUser();
                    productTypeListNewProductType.setCreatedUser(employee);
                    productTypeListNewProductType = em.merge(productTypeListNewProductType);
                    if (oldCreatedUserOfProductTypeListNewProductType != null && !oldCreatedUserOfProductTypeListNewProductType.equals(employee)) {
                        oldCreatedUserOfProductTypeListNewProductType.getProductTypeList().remove(productTypeListNewProductType);
                        oldCreatedUserOfProductTypeListNewProductType = em.merge(oldCreatedUserOfProductTypeListNewProductType);
                    }
                }
            }
            for (ProductType productTypeList1NewProductType : productTypeList1New) {
                if (!productTypeList1Old.contains(productTypeList1NewProductType)) {
                    Employee oldLastModifiedUserOfProductTypeList1NewProductType = productTypeList1NewProductType.getLastModifiedUser();
                    productTypeList1NewProductType.setLastModifiedUser(employee);
                    productTypeList1NewProductType = em.merge(productTypeList1NewProductType);
                    if (oldLastModifiedUserOfProductTypeList1NewProductType != null && !oldLastModifiedUserOfProductTypeList1NewProductType.equals(employee)) {
                        oldLastModifiedUserOfProductTypeList1NewProductType.getProductTypeList1().remove(productTypeList1NewProductType);
                        oldLastModifiedUserOfProductTypeList1NewProductType = em.merge(oldLastModifiedUserOfProductTypeList1NewProductType);
                    }
                }
            }
            for (AnalyticsTime analyticsTimeListNewAnalyticsTime : analyticsTimeListNew) {
                if (!analyticsTimeListOld.contains(analyticsTimeListNewAnalyticsTime)) {
                    Employee oldCreatedUserOfAnalyticsTimeListNewAnalyticsTime = analyticsTimeListNewAnalyticsTime.getCreatedUser();
                    analyticsTimeListNewAnalyticsTime.setCreatedUser(employee);
                    analyticsTimeListNewAnalyticsTime = em.merge(analyticsTimeListNewAnalyticsTime);
                    if (oldCreatedUserOfAnalyticsTimeListNewAnalyticsTime != null && !oldCreatedUserOfAnalyticsTimeListNewAnalyticsTime.equals(employee)) {
                        oldCreatedUserOfAnalyticsTimeListNewAnalyticsTime.getAnalyticsTimeList().remove(analyticsTimeListNewAnalyticsTime);
                        oldCreatedUserOfAnalyticsTimeListNewAnalyticsTime = em.merge(oldCreatedUserOfAnalyticsTimeListNewAnalyticsTime);
                    }
                }
            }
            for (AnalyticsTime analyticsTimeList1NewAnalyticsTime : analyticsTimeList1New) {
                if (!analyticsTimeList1Old.contains(analyticsTimeList1NewAnalyticsTime)) {
                    Employee oldLastModifiedUserOfAnalyticsTimeList1NewAnalyticsTime = analyticsTimeList1NewAnalyticsTime.getLastModifiedUser();
                    analyticsTimeList1NewAnalyticsTime.setLastModifiedUser(employee);
                    analyticsTimeList1NewAnalyticsTime = em.merge(analyticsTimeList1NewAnalyticsTime);
                    if (oldLastModifiedUserOfAnalyticsTimeList1NewAnalyticsTime != null && !oldLastModifiedUserOfAnalyticsTimeList1NewAnalyticsTime.equals(employee)) {
                        oldLastModifiedUserOfAnalyticsTimeList1NewAnalyticsTime.getAnalyticsTimeList1().remove(analyticsTimeList1NewAnalyticsTime);
                        oldLastModifiedUserOfAnalyticsTimeList1NewAnalyticsTime = em.merge(oldLastModifiedUserOfAnalyticsTimeList1NewAnalyticsTime);
                    }
                }
            }
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    Employee oldCreatedUserOfProductListNewProduct = productListNewProduct.getCreatedUser();
                    productListNewProduct.setCreatedUser(employee);
                    productListNewProduct = em.merge(productListNewProduct);
                    if (oldCreatedUserOfProductListNewProduct != null && !oldCreatedUserOfProductListNewProduct.equals(employee)) {
                        oldCreatedUserOfProductListNewProduct.getProductList().remove(productListNewProduct);
                        oldCreatedUserOfProductListNewProduct = em.merge(oldCreatedUserOfProductListNewProduct);
                    }
                }
            }
            for (Product productList1NewProduct : productList1New) {
                if (!productList1Old.contains(productList1NewProduct)) {
                    Employee oldLastModifiedUserOfProductList1NewProduct = productList1NewProduct.getLastModifiedUser();
                    productList1NewProduct.setLastModifiedUser(employee);
                    productList1NewProduct = em.merge(productList1NewProduct);
                    if (oldLastModifiedUserOfProductList1NewProduct != null && !oldLastModifiedUserOfProductList1NewProduct.equals(employee)) {
                        oldLastModifiedUserOfProductList1NewProduct.getProductList1().remove(productList1NewProduct);
                        oldLastModifiedUserOfProductList1NewProduct = em.merge(oldLastModifiedUserOfProductList1NewProduct);
                    }
                }
            }
            for (Provider providerListNewProvider : providerListNew) {
                if (!providerListOld.contains(providerListNewProvider)) {
                    Employee oldCreatedUserOfProviderListNewProvider = providerListNewProvider.getCreatedUser();
                    providerListNewProvider.setCreatedUser(employee);
                    providerListNewProvider = em.merge(providerListNewProvider);
                    if (oldCreatedUserOfProviderListNewProvider != null && !oldCreatedUserOfProviderListNewProvider.equals(employee)) {
                        oldCreatedUserOfProviderListNewProvider.getProviderList().remove(providerListNewProvider);
                        oldCreatedUserOfProviderListNewProvider = em.merge(oldCreatedUserOfProviderListNewProvider);
                    }
                }
            }
            for (Provider providerList1NewProvider : providerList1New) {
                if (!providerList1Old.contains(providerList1NewProvider)) {
                    Employee oldLastModifiedUserOfProviderList1NewProvider = providerList1NewProvider.getLastModifiedUser();
                    providerList1NewProvider.setLastModifiedUser(employee);
                    providerList1NewProvider = em.merge(providerList1NewProvider);
                    if (oldLastModifiedUserOfProviderList1NewProvider != null && !oldLastModifiedUserOfProviderList1NewProvider.equals(employee)) {
                        oldLastModifiedUserOfProviderList1NewProvider.getProviderList1().remove(providerList1NewProvider);
                        oldLastModifiedUserOfProviderList1NewProvider = em.merge(oldLastModifiedUserOfProviderList1NewProvider);
                    }
                }
            }
            for (Store storeListNewStore : storeListNew) {
                if (!storeListOld.contains(storeListNewStore)) {
                    Employee oldCreatedUserOfStoreListNewStore = storeListNewStore.getCreatedUser();
                    storeListNewStore.setCreatedUser(employee);
                    storeListNewStore = em.merge(storeListNewStore);
                    if (oldCreatedUserOfStoreListNewStore != null && !oldCreatedUserOfStoreListNewStore.equals(employee)) {
                        oldCreatedUserOfStoreListNewStore.getStoreList().remove(storeListNewStore);
                        oldCreatedUserOfStoreListNewStore = em.merge(oldCreatedUserOfStoreListNewStore);
                    }
                }
            }
            for (Store storeList1NewStore : storeList1New) {
                if (!storeList1Old.contains(storeList1NewStore)) {
                    Employee oldEmployeeOfStoreList1NewStore = storeList1NewStore.getEmployee();
                    storeList1NewStore.setEmployee(employee);
                    storeList1NewStore = em.merge(storeList1NewStore);
                    if (oldEmployeeOfStoreList1NewStore != null && !oldEmployeeOfStoreList1NewStore.equals(employee)) {
                        oldEmployeeOfStoreList1NewStore.getStoreList1().remove(storeList1NewStore);
                        oldEmployeeOfStoreList1NewStore = em.merge(oldEmployeeOfStoreList1NewStore);
                    }
                }
            }
            for (Store storeList2NewStore : storeList2New) {
                if (!storeList2Old.contains(storeList2NewStore)) {
                    Employee oldLastModifiedUserOfStoreList2NewStore = storeList2NewStore.getLastModifiedUser();
                    storeList2NewStore.setLastModifiedUser(employee);
                    storeList2NewStore = em.merge(storeList2NewStore);
                    if (oldLastModifiedUserOfStoreList2NewStore != null && !oldLastModifiedUserOfStoreList2NewStore.equals(employee)) {
                        oldLastModifiedUserOfStoreList2NewStore.getStoreList2().remove(storeList2NewStore);
                        oldLastModifiedUserOfStoreList2NewStore = em.merge(oldLastModifiedUserOfStoreList2NewStore);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = employee.getId();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Price> priceListOrphanCheck = employee.getPriceList();
            for (Price priceListOrphanCheckPrice : priceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Price " + priceListOrphanCheckPrice + " in its priceList field has a non-nullable createdUser field.");
            }
            List<Price> priceList1OrphanCheck = employee.getPriceList1();
            for (Price priceList1OrphanCheckPrice : priceList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Price " + priceList1OrphanCheckPrice + " in its priceList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Employee> employeeListOrphanCheck = employee.getEmployeeList();
            for (Employee employeeListOrphanCheckEmployee : employeeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Employee " + employeeListOrphanCheckEmployee + " in its employeeList field has a non-nullable createdUser field.");
            }
            List<Employee> employeeList1OrphanCheck = employee.getEmployeeList1();
            for (Employee employeeList1OrphanCheckEmployee : employeeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Employee " + employeeList1OrphanCheckEmployee + " in its employeeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<PriceType> priceTypeListOrphanCheck = employee.getPriceTypeList();
            for (PriceType priceTypeListOrphanCheckPriceType : priceTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the PriceType " + priceTypeListOrphanCheckPriceType + " in its priceTypeList field has a non-nullable createdUser field.");
            }
            List<PriceType> priceTypeList1OrphanCheck = employee.getPriceTypeList1();
            for (PriceType priceTypeList1OrphanCheckPriceType : priceTypeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the PriceType " + priceTypeList1OrphanCheckPriceType + " in its priceTypeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Record> recordListOrphanCheck = employee.getRecordList();
            for (Record recordListOrphanCheckRecord : recordListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Record " + recordListOrphanCheckRecord + " in its recordList field has a non-nullable createdUser field.");
            }
            List<Record> recordList1OrphanCheck = employee.getRecordList1();
            for (Record recordList1OrphanCheckRecord : recordList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Record " + recordList1OrphanCheckRecord + " in its recordList1 field has a non-nullable employee field.");
            }
            List<Record> recordList2OrphanCheck = employee.getRecordList2();
            for (Record recordList2OrphanCheckRecord : recordList2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Record " + recordList2OrphanCheckRecord + " in its recordList2 field has a non-nullable lastModifiedUser field.");
            }
            List<Invoice> invoiceListOrphanCheck = employee.getInvoiceList();
            for (Invoice invoiceListOrphanCheckInvoice : invoiceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Invoice " + invoiceListOrphanCheckInvoice + " in its invoiceList field has a non-nullable createdUser field.");
            }
            List<Invoice> invoiceList1OrphanCheck = employee.getInvoiceList1();
            for (Invoice invoiceList1OrphanCheckInvoice : invoiceList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Invoice " + invoiceList1OrphanCheckInvoice + " in its invoiceList1 field has a non-nullable employee field.");
            }
            List<Invoice> invoiceList2OrphanCheck = employee.getInvoiceList2();
            for (Invoice invoiceList2OrphanCheckInvoice : invoiceList2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Invoice " + invoiceList2OrphanCheckInvoice + " in its invoiceList2 field has a non-nullable lastModifiedUser field.");
            }
            List<Client> clientListOrphanCheck = employee.getClientList();
            for (Client clientListOrphanCheckClient : clientListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Client " + clientListOrphanCheckClient + " in its clientList field has a non-nullable createdUser field.");
            }
            List<Client> clientList1OrphanCheck = employee.getClientList1();
            for (Client clientList1OrphanCheckClient : clientList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Client " + clientList1OrphanCheckClient + " in its clientList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Stock> stockListOrphanCheck = employee.getStockList();
            for (Stock stockListOrphanCheckStock : stockListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Stock " + stockListOrphanCheckStock + " in its stockList field has a non-nullable createdUser field.");
            }
            List<Stock> stockList1OrphanCheck = employee.getStockList1();
            for (Stock stockList1OrphanCheckStock : stockList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Stock " + stockList1OrphanCheckStock + " in its stockList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Brand> brandListOrphanCheck = employee.getBrandList();
            for (Brand brandListOrphanCheckBrand : brandListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Brand " + brandListOrphanCheckBrand + " in its brandList field has a non-nullable createdUser field.");
            }
            List<Brand> brandList1OrphanCheck = employee.getBrandList1();
            for (Brand brandList1OrphanCheckBrand : brandList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Brand " + brandList1OrphanCheckBrand + " in its brandList1 field has a non-nullable lastModifiedUser field.");
            }
            List<SexType> sexTypeListOrphanCheck = employee.getSexTypeList();
            for (SexType sexTypeListOrphanCheckSexType : sexTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the SexType " + sexTypeListOrphanCheckSexType + " in its sexTypeList field has a non-nullable createdUser field.");
            }
            List<SexType> sexTypeList1OrphanCheck = employee.getSexTypeList1();
            for (SexType sexTypeList1OrphanCheckSexType : sexTypeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the SexType " + sexTypeList1OrphanCheckSexType + " in its sexTypeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<PaymentType> paymentTypeListOrphanCheck = employee.getPaymentTypeList();
            for (PaymentType paymentTypeListOrphanCheckPaymentType : paymentTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the PaymentType " + paymentTypeListOrphanCheckPaymentType + " in its paymentTypeList field has a non-nullable createdUser field.");
            }
            List<PaymentType> paymentTypeList1OrphanCheck = employee.getPaymentTypeList1();
            for (PaymentType paymentTypeList1OrphanCheckPaymentType : paymentTypeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the PaymentType " + paymentTypeList1OrphanCheckPaymentType + " in its paymentTypeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<EmployeeType> employeeTypeListOrphanCheck = employee.getEmployeeTypeList();
            for (EmployeeType employeeTypeListOrphanCheckEmployeeType : employeeTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeType " + employeeTypeListOrphanCheckEmployeeType + " in its employeeTypeList field has a non-nullable createdUser field.");
            }
            List<EmployeeType> employeeTypeList1OrphanCheck = employee.getEmployeeTypeList1();
            for (EmployeeType employeeTypeList1OrphanCheckEmployeeType : employeeTypeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeType " + employeeTypeList1OrphanCheckEmployeeType + " in its employeeTypeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Item> itemListOrphanCheck = employee.getItemList();
            for (Item itemListOrphanCheckItem : itemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Item " + itemListOrphanCheckItem + " in its itemList field has a non-nullable createdUser field.");
            }
            List<Item> itemList1OrphanCheck = employee.getItemList1();
            for (Item itemList1OrphanCheckItem : itemList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Item " + itemList1OrphanCheckItem + " in its itemList1 field has a non-nullable lastModifiedUser field.");
            }
            List<ProductType> productTypeListOrphanCheck = employee.getProductTypeList();
            for (ProductType productTypeListOrphanCheckProductType : productTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the ProductType " + productTypeListOrphanCheckProductType + " in its productTypeList field has a non-nullable createdUser field.");
            }
            List<ProductType> productTypeList1OrphanCheck = employee.getProductTypeList1();
            for (ProductType productTypeList1OrphanCheckProductType : productTypeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the ProductType " + productTypeList1OrphanCheckProductType + " in its productTypeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<AnalyticsTime> analyticsTimeListOrphanCheck = employee.getAnalyticsTimeList();
            for (AnalyticsTime analyticsTimeListOrphanCheckAnalyticsTime : analyticsTimeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the AnalyticsTime " + analyticsTimeListOrphanCheckAnalyticsTime + " in its analyticsTimeList field has a non-nullable createdUser field.");
            }
            List<AnalyticsTime> analyticsTimeList1OrphanCheck = employee.getAnalyticsTimeList1();
            for (AnalyticsTime analyticsTimeList1OrphanCheckAnalyticsTime : analyticsTimeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the AnalyticsTime " + analyticsTimeList1OrphanCheckAnalyticsTime + " in its analyticsTimeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Product> productListOrphanCheck = employee.getProductList();
            for (Product productListOrphanCheckProduct : productListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Product " + productListOrphanCheckProduct + " in its productList field has a non-nullable createdUser field.");
            }
            List<Product> productList1OrphanCheck = employee.getProductList1();
            for (Product productList1OrphanCheckProduct : productList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Product " + productList1OrphanCheckProduct + " in its productList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Provider> providerListOrphanCheck = employee.getProviderList();
            for (Provider providerListOrphanCheckProvider : providerListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Provider " + providerListOrphanCheckProvider + " in its providerList field has a non-nullable createdUser field.");
            }
            List<Provider> providerList1OrphanCheck = employee.getProviderList1();
            for (Provider providerList1OrphanCheckProvider : providerList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Provider " + providerList1OrphanCheckProvider + " in its providerList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Store> storeListOrphanCheck = employee.getStoreList();
            for (Store storeListOrphanCheckStore : storeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Store " + storeListOrphanCheckStore + " in its storeList field has a non-nullable createdUser field.");
            }
            List<Store> storeList1OrphanCheck = employee.getStoreList1();
            for (Store storeList1OrphanCheckStore : storeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Store " + storeList1OrphanCheckStore + " in its storeList1 field has a non-nullable employee field.");
            }
            List<Store> storeList2OrphanCheck = employee.getStoreList2();
            for (Store storeList2OrphanCheckStore : storeList2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Store " + storeList2OrphanCheckStore + " in its storeList2 field has a non-nullable lastModifiedUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = employee.getCreatedUser();
            if (createdUser != null) {
                createdUser.getEmployeeList().remove(employee);
                createdUser = em.merge(createdUser);
            }
            EmployeeType employeeType = employee.getEmployeeType();
            if (employeeType != null) {
                employeeType.getEmployeeList().remove(employee);
                employeeType = em.merge(employeeType);
            }
            SexType sexType = employee.getSexType();
            if (sexType != null) {
                sexType.getEmployeeList().remove(employee);
                sexType = em.merge(sexType);
            }
            Employee lastModifiedUser = employee.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getEmployeeList().remove(employee);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            Language languageType = employee.getLanguageType();
            if (languageType != null) {
                languageType.getEmployeeList().remove(employee);
                languageType = em.merge(languageType);
            }
            em.remove(employee);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee findEmployee(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
