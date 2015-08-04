package com.development.simplestockmanager.common.web.object.validator.common;

import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.language.LanguageControllerManager;
import com.development.simplestockmanager.web.service.MessageService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 * Common class for validator
 *
 * @author foxtrot
 */
public class CommonValidator {

    protected MessageService messageService;
    protected long mode;
    private List<FacesMessage> messageList;

    public CommonValidator(long mode) {
        this.mode = mode;
        this.messageService = new MessageService();
    }

    protected boolean validate(List<String> warningList, List<String> errorList) {
        messageList = new ArrayList<>();
        String detail;
        String summary;

        if (!warningList.isEmpty()) {
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.WARNING);
            if (warningList.size() == 1) {
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.WARNING.SINGULAR);
            } else {
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.WARNING.PLURAL);
            }

            messageList.add(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));

            for (String warning : warningList) {
                messageList.add(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, warning));
            }
        }

        if (!errorList.isEmpty()) {
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.ERROR);
            if (errorList.size() == 1) {
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.ERROR.SINGULAR);
            } else {
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.ERROR.PLURAL);
            }

            messageList.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));

            for (String error : errorList) {
                messageList.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, error));
            }
        }

        return messageList.isEmpty();
    }

    public List<FacesMessage> getMessageList() {
        return messageList;
    }
}
