package com.development.simplestockmanager.web.common.service.general;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.WebConstant;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for button internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "button")
@SessionScoped
public class ButtonService implements Serializable {

    private final String add;
    private final String cancel;
    private final String edit;
    private final String finish;
    private final String search;
    private final String clear;

    public ButtonService() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.BUTTON.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguage().getCode());

        add = controller.getWord(CommonConstant.BUTTON.ADD);
        cancel = controller.getWord(CommonConstant.BUTTON.CANCEL);
        edit = controller.getWord(CommonConstant.BUTTON.EDIT);
        finish = controller.getWord(CommonConstant.BUTTON.FINISH);
        search = controller.getWord(CommonConstant.BUTTON.SEARCH);
        clear = controller.getWord(CommonConstant.BUTTON.CLEAR);
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
    
}
