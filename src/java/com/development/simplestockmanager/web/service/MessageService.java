package com.development.simplestockmanager.web.service;

import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.language.LanguageControllerManager;
import java.io.Serializable;

/**
 * Service class for internationalization functionality
 *
 * @author foxtrot
 */
public class MessageService implements Serializable {

    private final LanguageController controller;
    private final String messageBase;
    private final String messageComment;

    public MessageService() {
        controller = LanguageControllerManager.getInstance().getController();
        messageBase = controller.getWord(CommonConstant.MESSAGE.DETAIL.BASE);
        messageComment = controller.getWord(CommonConstant.MESSAGE.DETAIL.COMMENT);
    }

    public String getSummary(String type) {
        return controller.getWord(type);
    }

    public String getDetail(String type) {
        return controller.getWord(type);
    }

    public String getDetail(String label, String comment) {
        return replaceDetail(controller.getWord(label), (comment == null ? "" : controller.getWord(comment)));
    }

    public String getDetail(String object, Long id, String type) {
        return replaceDetail(controller.getWord(type), object, id);
    }

    private String replaceDetail(String label, String comment) {
        String detail;

        detail = messageBase.replace(CommonConstant.VARIANT.TYPE.CODE, label);
        
        if (!comment.isEmpty()) {
            detail = detail.concat(messageComment.replace(CommonConstant.VARIANT.COMMENT.CODE, comment));
        }
        
        return detail;
    }

    private String replaceDetail(String base, String object, Long id) {
        String detail;
        
        detail = base.replace(CommonConstant.VARIANT.ARTICLE.CODE, controller.getWord(object + CommonConstant.VARIANT.ARTICLE.TEXT));
        detail = detail.replace(CommonConstant.VARIANT.IDENTIFIER.CODE, id.toString());

        return detail;
    }

}
