package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.Constant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Validator class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeValidator extends BaseValidator {

    private final EmployeeSpecificController specificController;
    private Employee employee;

    public EmployeeValidator(long mode, InternationalizationController controller) {
        super(mode, controller);
        specificController = new EmployeeSpecificController();
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
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.FIRSTNAME));
        }

        if (employee.getLastname().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.LASTNAME));
        }

        if (employee.getPhone().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }

        if (employee.getEmail().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.EMAIL));
        }

        if (employee.getUsername().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.USERNAME));
        }

        if (employee.getPassword().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.PASSWORD));
        }

        if (employee.getBornDate() == null) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.BORN_DATE));
        }

        if (employee.getSexType().getId() == Constant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.SEX_TYPE));
        }
        
        if (employee.getEmployeeType().getId() == Constant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.EMPLOYEE_TYPE));
        }
        
        if (employee.getLanguageType().getId() == Constant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.LANGUAGE_TYPE));
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (employee.getBornDate() != null) {
            if (employee.getBornDate().after(new Date())) {
                causeList.add(controller.getWord(InternationalizationConstant.MESSAGE.ERROR.BORN_DATE));
            }
        }

        if (!employee.getUsername().isEmpty()) {
            if (!specificController.usernameIsAvailable(employee.getUsername())) {
                causeList.add(controller.getWord(InternationalizationConstant.MESSAGE.ERROR.USERNAME));
            }
        }

        return causeList;
    }

}
