package com.development.simplestockmanager.common.web.object.validator.common;

import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 * Common class for validator
 *
 * @author foxtrot
 */
public class CommonValidator {

    protected LanguageController languageController;
    protected long mode;
    private List<FacesMessage> messageList;

    public CommonValidator(long mode, LanguageController controller) {
        this.mode = mode;
        this.languageController = controller;
    }

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

    public List<FacesMessage> getMessageList() {
        return messageList;
    }
}
