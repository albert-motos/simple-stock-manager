package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.web.object.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Validator class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeValidator extends BaseValidator {

    private Employee employee;

    public EmployeeValidator(long mode) {
        super(mode, "");
    }

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

        if (employee.getUsername().isEmpty()) {
            fieldsEmptyList.add("Username");
        }

        if (employee.getPassword().isEmpty()) {
            fieldsEmptyList.add("Password");
        }

        if (employee.getBornDate() == null) {
            fieldsEmptyList.add("Born date");
        }

        if (employee.getSexType() == -1) {
            fieldsEmptyList.add("Sex type selector: this selector is not indicated");
        }

        if (employee.getEmployeeType() == -1) {
            fieldsEmptyList.add("Employee type selector: this selector is not indicated");
        }

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
            if (!new EmployeeSpecificController().usernameIsAvailable(employee.getUsername())) {
                causeList.add("User name: This user name is already in use, change it");
            }
        }

        return causeList;
    }

}
