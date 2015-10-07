package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.web.service.general.*;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for button internationalization functionality
 *
 * @author foxtrot
 */

public class Button {

    private final String add;
    private final String cancel;
    private final String edit;
    private final String finish;
    private final String search;
    private final String clear;
    private final String select;

    public Button() {
        LanguageController controller = LanguageControllerManager.getInstance().getController();

        add = controller.getWord(CommonConstant.BUTTON.ADD);
        cancel = controller.getWord(CommonConstant.BUTTON.CANCEL);
        edit = controller.getWord(CommonConstant.BUTTON.EDIT);
        finish = controller.getWord(CommonConstant.BUTTON.FINISH);
        search = controller.getWord(CommonConstant.BUTTON.SEARCH);
        clear = controller.getWord(CommonConstant.BUTTON.CLEAR);
        select = controller.getWord(CommonConstant.BUTTON.SELECT);
    }

    public String getAdd() {
        return add;
    }

    public String getEdit() {
        return edit;
    }

    public String getSearch() {
        return search;
    }

    public String back(boolean end) {
        return (end ? finish : cancel);
    }

    public String getClear() {
        return clear;
    }

    public String getSelect() {
        return select;
    }
    
}
