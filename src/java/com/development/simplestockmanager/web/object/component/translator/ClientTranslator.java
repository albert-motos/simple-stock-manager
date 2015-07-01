package com.development.simplestockmanager.web.object.component.translator;

import com.development.simplestockmanager.common.Constant;
import com.development.simplestockmanager.common.language.LanguageController;

/**
 *
 * @author foxtrot
 */
public class ClientTranslator {
    
    private final String headerAttributes;
    private final String headerVisibility;
    
    private final String labelEnable;

    public ClientTranslator(String language) {
        LanguageController controller = new LanguageController(language);
        headerAttributes = controller.getWord(Constant.LANGUAGE.HEADER.CLIENT.ATTRIBUTES);
        headerVisibility = controller.getWord(Constant.LANGUAGE.HEADER.CLIENT.VISIBILITY);
        
        labelEnable = controller.getWord(Constant.LANGUAGE.LABEL.ENABLE.CLIENT);
    }

    public String getHeaderAttributes() {
        return headerAttributes;
    }

    public String getHeaderVisibility() {
        return headerVisibility;
    }

    public String getLabelEnable() {
        return labelEnable;
    }
    
}
