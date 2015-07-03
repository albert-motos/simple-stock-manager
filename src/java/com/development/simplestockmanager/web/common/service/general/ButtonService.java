package com.development.simplestockmanager.web.common.service.general;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.Constant;
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
    private final String finish;

    public ButtonService() {
        System.out.println("# " + new Date() + " | " + Constant.LOGGER.SERVICE.BUTTON.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());

        add = controller.getWord(InternationalizationConstant.BUTTON.ADD);
        cancel = controller.getWord(InternationalizationConstant.BUTTON.CANCEL);
        finish = controller.getWord(InternationalizationConstant.BUTTON.FINISH);
    }

    public String getAdd() {
        return add;
    }

    public String back(boolean end) {
        return (end ? finish : cancel);
    }
}
