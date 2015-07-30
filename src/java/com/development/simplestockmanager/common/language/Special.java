package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.constant.CommonConstant;

/**
 * Service class for button internationalization functionality
 *
 * @author foxtrot
 */
public class Special {

    private final String non_selection;
    private final String between;
    private final String conjuction;
    private final String counter;
    
    private final String visible;
    private final String hidden;
    private final String actions;
    private final Value value;
    private final Auditory auditory;

    public Special() {
        LanguageController controller = LanguageControllerManager.getInstance().getController();

        non_selection = controller.getWord(CommonConstant.SPECIAL.NON_SELECTION);
        between = controller.getWord(CommonConstant.SPECIAL.BETWEEN);
        conjuction = controller.getWord(CommonConstant.SPECIAL.CONJUCTION);
        counter = controller.getWord(CommonConstant.SPECIAL.COUNTER);
        visible = controller.getWord(CommonConstant.SPECIAL.VISIBLE);
        hidden = controller.getWord(CommonConstant.SPECIAL.HIDDEN);
        actions = controller.getWord(CommonConstant.SPECIAL.ACTIONS);

        value = new Value();
        auditory = new Auditory(controller.getWord(CommonConstant.LABEL.BASE));
    }

    public String getStatus(boolean enable) {
        return (enable ? visible : hidden);
    }

    public String getNon_selection() {
        return non_selection;
    }

    public String getBetween() {
        return between;
    }

    public String getConjuction() {
        return conjuction;
    }

    public String getCounter() {
        return counter;
    }

    public String getVisible() {
        return visible;
    }

    public String getHidden() {
        return hidden;
    }

    public Value getValue() {
        return value;
    }

    public Auditory getAuditory() {
        return auditory;
    }

    public String getActions() {
        return actions;
    }

}
