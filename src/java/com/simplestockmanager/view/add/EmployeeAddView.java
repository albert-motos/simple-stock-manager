/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.add;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.controller.general.EmployeeGeneralController;
import com.simplestockmanager.data.controller.general.EmployeeTypeGeneralController;
import com.simplestockmanager.data.controller.general.SexTypeGeneralController;
import com.simplestockmanager.data.controller.specific.EmployeeSpecificController;
import com.simplestockmanager.persistence.Employee;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class EmployeeAddView implements AddView {

    private Employee employee;
    private String sexTypeSelection;
    private String employeeTypeSelection;
    private boolean added;

    public EmployeeAddView() {
        employee = new Employee();
        added = false;
    }

    @Override
    public void add() {
        if (validate()) {
            long id = EmployeeGeneralController.create(employee.getFirstName(), employee.getLastName(), employee.getBornDate(),
                    SexTypeGeneralController.create(sexTypeSelection), employee.getPhone(), employee.getEmail(),
                    EmployeeTypeGeneralController.create(employeeTypeSelection), employee.getIsEnable(), new Date(), new Date(), employee.getUserName(),
                    employee.getPassword(), false, new Date(), "");

            if (id == Constant.IDENTIFIER.INVALID) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "You can not create employee right now"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Employee [" + id + "] is created properly"));
                added = true;
            }
        }
    }

    @Override
    public boolean validate() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();

        String fields_empty = "";

        fields_empty = fields_empty.concat((employee.getFirstName().isEmpty() ? "First_name " : ""));
        fields_empty = fields_empty.concat((employee.getLastName().isEmpty() ? "Last_name " : ""));
        fields_empty = fields_empty.concat((employee.getPhone().isEmpty() ? "Phone_number " : ""));
        fields_empty = fields_empty.concat((employee.getEmail().isEmpty() ? "Email " : ""));
        fields_empty = fields_empty.concat((employee.getUserName().isEmpty() ? "User_name " : ""));
        fields_empty = fields_empty.concat((employee.getPassword().isEmpty() ? "Password " : ""));

        if (!fields_empty.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next field/s couldn't be empty: " + fields_empty));
        }

        if (employee.getBornDate() == null) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The born_date is not indicated"));
        } else {
            if (employee.getBornDate().after(new Date())) {
                currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The born_date is incorrect, the client don't born yet"));
            }
        }

        if (sexTypeSelection.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The sex_type_selector is not indicated"));
        }

        if (employeeTypeSelection.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The employee_type_selector is not indicated"));
        }

        if (!employee.getUserName().isEmpty()) {
            if (!EmployeeSpecificController.userNameIsAvailable(employee.getUserName())) {
                currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The user_name is already in use, change it"));
            }
        }

        return currentInstance.getMessageList().isEmpty();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSexTypeSelection() {
        return sexTypeSelection;
    }

    public void setSexTypeSelection(String sexTypeSelection) {
        this.sexTypeSelection = sexTypeSelection;
    }

    public String getEmployeeTypeSelection() {
        return employeeTypeSelection;
    }

    public void setEmployeeTypeSelection(String employeeTypeSelection) {
        this.employeeTypeSelection = employeeTypeSelection;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

}
