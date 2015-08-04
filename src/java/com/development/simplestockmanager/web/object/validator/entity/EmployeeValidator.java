package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeValidator extends CommonValidator implements BaseValidator {

    private final EmployeeSpecificController specificController;
    private Employee employee;

    public EmployeeValidator(long mode, EmployeeSpecificController specificController) {
        super(mode);
        this.specificController = specificController;
    }

    @Override
    public void setObject(Object object) {
        employee = (Employee) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (employee.getFirstname().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.FIRSTNAME, null));
        }

        if (employee.getLastname().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.LASTNAME, null));
        }

        if (employee.getPhone().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PHONE_NUMBER, null));
        }

        if (employee.getEmail().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.EMAIL, null));
        }

        if (employee.getUsername().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.USERNAME, null));
        }

        if (employee.getPassword().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PASSWORD, null));
        }

        if (employee.getBornDate() == null) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.BORN_DATE, null));
        }

        if (employee.getSexType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.SEX_TYPE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }

        if (employee.getEmployeeType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.EMPLOYEE_TYPE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }

        if (employee.getLanguage().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.LANGUAGE_TYPE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (employee.getBornDate() != null) {
            if (employee.getBornDate().after(new Date())) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.BORN_DATE, CommonConstant.MESSAGE.DETAIL.ERROR.DATE));
            }
        }

        if (!employee.getUsername().isEmpty()) {
            Employee employeeOfUsername = specificController.findByUsername(employee.getUsername());

            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && employeeOfUsername.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && employeeOfUsername.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(employeeOfUsername.getId(), employee.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.USERNAME, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }

        return causeList;
    }

}
