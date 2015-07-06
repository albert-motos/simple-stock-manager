package com.development.simplestockmanager.web.common.service.general;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
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
    private final String find;
    private final String cancel;
    private final String finish;

    public ButtonService() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.BUTTON.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());

        add = controller.getWord(CommonConstant.BUTTON.ADD);
        cancel = controller.getWord(CommonConstant.BUTTON.CANCEL);
        finish = controller.getWord(CommonConstant.BUTTON.FINISH);
        find = controller.getWord(CommonConstant.BUTTON.FIND);;
    }

    public String getAdd() {
        return add;
    }

    public String getFind() {
        return find;
    }

    public String back(boolean end) {
        return (end ? finish : cancel);
    }
}
