package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeValidator extends BaseValidator {

    private final EmployeeSpecificController specificController;
    private Employee employee;

    public EmployeeValidator(long mode, LanguageController controller, EmployeeSpecificController specificController) {
        super(mode, controller);
        this.specificController = specificController;
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
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.FIRSTNAME));
        }

        if (employee.getLastname().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.LASTNAME));
        }

        if (employee.getPhone().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }

        if (employee.getEmail().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.EMAIL));
        }

        if (employee.getUsername().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.USERNAME));
        }

        if (employee.getPassword().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.PASSWORD));
        }

        if (employee.getBornDate() == null) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.BORN_DATE));
        }

        if (employee.getSexType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.SEX_TYPE));
        }
        
        if (employee.getEmployeeType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.EMPLOYEE_TYPE));
        }
        
        if (employee.getLanguageType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.LANGUAGE_TYPE));
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (employee.getBornDate() != null) {
            if (employee.getBornDate().after(new Date())) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.BORN_DATE));
            }
        }

        if (!employee.getUsername().isEmpty()) {
            Employee employeeOfUsername = specificController.findByUsername(employee.getUsername());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && employeeOfUsername.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && employeeOfUsername.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(employeeOfUsername.getId(), employee.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.USERNAME));
            }
        }

        return causeList;
    }

}
