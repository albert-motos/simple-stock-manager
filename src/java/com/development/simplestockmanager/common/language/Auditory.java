package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.constant.CommonConstant;

/**
 * Service class for button internationalization functionality
 *
 * @author foxtrot
 */

public class Auditory {

    private final String header;
    private final String created_user;
    private final String last_modified_user;
    private final String created_date;
    private final String last_modified_date;

    public Auditory(String base) {
        LanguageController controller = LanguageControllerManager.getInstance().getController();

        header = controller.getWord(CommonConstant.SPECIAL.AUDITORY.HEADER);
        created_user = replace(base, controller.getWord(CommonConstant.SPECIAL.AUDITORY.CREATED_USER));
        last_modified_user = replace(base, controller.getWord(CommonConstant.SPECIAL.AUDITORY.LAST_MODIFIED_USER));
        created_date = replace(base, controller.getWord(CommonConstant.SPECIAL.AUDITORY.CREATED_DATE));
        last_modified_date = replace(base, controller.getWord(CommonConstant.SPECIAL.AUDITORY.LAST_MODIFIED_DATE));
    }

    private String replace(String base, String type) {        
        return base.replace(CommonConstant.VARIANT.TYPE.CODE, type);
    }
    
    public String getHeader() {
        return header;
    }

    public String getCreated_user() {
        return created_user;
    }

    public String getLast_modified_user() {
        return last_modified_user;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getLast_modified_date() {
        return last_modified_date;
    }

}
