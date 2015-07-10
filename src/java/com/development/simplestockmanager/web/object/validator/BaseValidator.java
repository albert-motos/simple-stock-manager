/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Monica
 */
abstract class BaseValidator {

    protected LanguageController languageController;
    protected long mode;
    protected Object object;
    private List<FacesMessage> messageList;

    public BaseValidator(long mode, LanguageController controller) {
        this.mode = mode;
        this.languageController = controller;
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
        String detail;
        String summary;

        if (!warningList.isEmpty()) {
            summary = languageController.getWord(CommonConstant.MESSAGE.WARNING.SUMMARY);
            if (warningList.size() == 1) {
                detail = languageController.getWord(CommonConstant.MESSAGE.WARNING.DETAIL.SINGULAR);
            } else {
                detail = languageController.getWord(CommonConstant.MESSAGE.WARNING.DETAIL.PLURAL);
            }
            
            messageList.add(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));

            for (String warning : warningList) {
                messageList.add(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "   -   " + warning));
            }
        }

        if (!errorList.isEmpty()) {
            summary = languageController.getWord(CommonConstant.MESSAGE.ERROR.SUMMARY);
            if (errorList.size() == 1) {
                detail = languageController.getWord(CommonConstant.MESSAGE.ERROR.DETAIL.SINGULAR);
            } else {
                detail = languageController.getWord(CommonConstant.MESSAGE.ERROR.DETAIL.PLURAL);
            }
            
            messageList.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));

            for (String error : errorList) {
                messageList.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, "   -   " + error));
            }
        }

        return messageList.isEmpty();
    }

    abstract protected List<String> checkFields();

    protected List<String> inconsistenceFields() {
        return new ArrayList<>();
    }
}
