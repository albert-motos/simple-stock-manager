/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.object.validator;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Monica
 */
abstract class BaseValidator {
    
    protected long mode;
    protected Object object;
    private List<FacesMessage> messageList;

    public BaseValidator(long mode) {
        this.mode = mode;
    }
    
    public void setObject(Object object) {
        this.object = object;
    }
    
    public List<FacesMessage> getMessageList() {
        return messageList;
    }
    
    abstract protected void convertObject();
    
    abstract public boolean validate();
    
    protected boolean validate(List<String> warningList, List<String> errorList) {
        messageList = new ArrayList<>();
        
        if (!warningList.isEmpty()) {
            messageList.add(new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next " + (warningList.size() > 1 ? "fields" : "field") + " couldn't be empty:"));
            
            for (String warning : warningList) {
                messageList.add(new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "   -   " + warning));
            }
        }
        
        if (!errorList.isEmpty()) {
            messageList.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The next " + (errorList.size() > 1 ? "fields have" : "field has") + " erroneous information:"));
            
            for (String error : errorList) {
                messageList.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "   -   " + error));
            }
        }
        
        return messageList.isEmpty();
    }
    
    abstract protected List<String> checkFields();
    
    protected List<String> inconsistenceFields() {
        return new ArrayList<>();
    }
}
