package com.development.simplestockmanager.common;


/**
 *
 * @author foxtrot
 */
public interface Constant {

    public interface LANGUAGE {

        public static String PATH = "com.development.simplestockmanager.common.language.language";
        
        public interface CLIENT {
            
            public static String TEXT = "client.";
            public interface HEADER {
                
                public static String TEXT = "header." + CLIENT.TEXT;
                public static String ATTRIBUTES = HEADER.TEXT + "attributes";
            } 
            
        }
    }
}
