package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.web.controller.common.SessionController;
import java.util.HashMap;

public class LanguageControllerManager {
    
    private static volatile LanguageControllerManager instance = new LanguageControllerManager();
    private HashMap<String, String> sessionLanguageMap = new HashMap<>();
    
    // private constructor
    private LanguageControllerManager() {
    }

    public static LanguageControllerManager getInstance() {
        return instance;
    }

    public void addSession(String session, String language) {
        sessionLanguageMap.put(session, language);
    }
    
    public void removeSession(String session) {
        sessionLanguageMap.remove(session);
    }
    
    public LanguageController getController() {
        String session = SessionController.getSessionId();
        
        return new LanguageController(sessionLanguageMap.get(session));
    }
    
}