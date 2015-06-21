package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.persistence.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeValidator extends BaseValidator {

    private Employee employee;

    @Override
    protected void convertObject() {
        employee = (Employee) object;
    }

    @Override
    public boolean validate() {
        convertObject();
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    protected List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (employee.getFirstname().isEmpty()) {
            fieldsEmptyList.add("First name");
        }

        if (employee.getLastname().isEmpty()) {
            fieldsEmptyList.add("Last name");
        }

        if (employee.getPhone().isEmpty()) {
            fieldsEmptyList.add("Phone number");
        }

        if (employee.getEmail().isEmpty()) {
            fieldsEmptyList.add("Email");
        }

//        if (employee.getUserName().isEmpty()) {
//            fieldsEmptyList.add("User name");
//        }
//
//        if (employee.getPassword().isEmpty()) {
//            fieldsEmptyList.add("Password");
//        }
//
//        if (employee.getBornDate() == null) {
//            fieldsEmptyList.add("Born date");
//        }
//
//        if (employee.getSexTypeID() == 0) {
//            fieldsEmptyList.add("Sex type selector: this selector is not indicated");
//        }
//
//        if (employee.getEmployeTypeID() == 0) {
//            fieldsEmptyList.add("Employee type selector: this selector is not indicated");
//        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (employee.getBornDate() != null) {
            if (employee.getBornDate().after(new Date())) {
                causeList.add("Born date: The date may not be later than today");
            }
        }

        if (!employee.getUsername().isEmpty()) {
            if (!EmployeeSpecificController.userNameIsAvailable(employee.getUsername())) {
                causeList.add("User name: This user name is already in use, change it");
            }
        }

        return causeList;
    }

}
